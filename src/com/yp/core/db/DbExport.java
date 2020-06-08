package com.yp.core.db;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.yp.core.AModel;
import com.yp.core.BaseConstants;
import com.yp.core.db.OnExportListener.PHASE;
import com.yp.core.entity.IResult;
import com.yp.core.tools.DateTime;

public class DbExport {

	private ConcurrentHashMap<String, FutureTask<IResult<IExport>>> exports;
	private ExecutorService executor;
	private int taskCount;

	public DbExport(int pTaskCount) {
		super();
		taskCount = pTaskCount;
		exports = new ConcurrentHashMap<>(taskCount);
		executor = Executors.newFixedThreadPool(taskCount);
	}

	private static final String FORMATED_EXPORT_MESSAGE1 = "TABLO TOPLAMI/TAMAMLANAN :%s/%s, %s";

	public void export(DbConninfo pTarget, IExport pExport, OnExportListener proceedListener) {
		if (pExport != null) {
			FutureTask<IResult<IExport>> task = new FutureTask<>(new Callable<IResult<IExport>>() {
				@Override
				public IResult<IExport> call() throws Exception {
					return new AModel<IExport>() {
					}.exportDb(pTarget, pExport, proceedListener);
				}
			});

			exports.put(pExport.getExportId(), task);
			executor.execute(task);
			while (true) {
				try {
					if (task.isDone()) {
						IResult<IExport> res = task.get();
						pExport.setMessages(res.getMessage());
						pExport.setEndTime(DateTime.dbNow());
						exports.remove(pExport.getExportId());
						int remaining = exports.size();
						proceedListener.onProceed(PHASE.ENDS_ALL, (double) remaining, taskCount, String
								.format(FORMATED_EXPORT_MESSAGE1, taskCount, taskCount - remaining, res.getMessage()));
						return;
					}
					if (task.isCancelled()) {
						IResult<IExport> res = task.get();
						pExport.setMessages(res.getMessage());
						exports.remove(pExport.getExportId());
						int remaining = exports.size();
						proceedListener.onProceed(PHASE.FAILS_ALL, (double) remaining, taskCount, String
								.format(FORMATED_EXPORT_MESSAGE1, taskCount, taskCount - remaining, res.getMessage()));

					}
				} catch (Exception e) {
					pExport.setMessages(e.getMessage());
					exports.remove(pExport.getExportId());
					int remaining = exports.size();
					proceedListener.onProceed(PHASE.FAILS_ALL, (double) remaining, taskCount,
							String.format(FORMATED_EXPORT_MESSAGE1, taskCount, taskCount - remaining, e.getMessage()));
				}
			}
		}
	}

	public void cancelExport(List<IExport> list) {
		if (!BaseConstants.isEmpty(list)) {
			list.forEach(e -> exports.get(e.getExportId()).cancel(true));
		}
	}
}
