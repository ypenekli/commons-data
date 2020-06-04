package com.yp.core.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yp.core.AModel;
import com.yp.core.BaseConstants;
import com.yp.core.db.OnExportListener.PHASE;
import com.yp.core.entity.IResult;
import com.yp.core.tools.DateTime;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class DbExport {

	private Map<String, Service<IResult<IExport>>> exports;
	private int taskCount;

	public DbExport(int pTaskCount) {
		super();
		taskCount = pTaskCount;
		exports = new HashMap<>(taskCount);
	}

	private static final String FORMATED_EXPORT_MESSAGE1 = "TABLO TOPLAMI/TAMAMLANAN :%s/%s, %s ";

	public void export(DbConninfo pTarget, IExport pExport, OnExportListener proceedListener) {
		if (pExport != null) {
			Service<IResult<IExport>> export = new Service<IResult<IExport>>() {
				@Override
				protected Task<IResult<IExport>> createTask() {
					return new Task<IResult<IExport>>() {
						@Override
						protected IResult<IExport> call() throws Exception {
							return new AModel<IExport>() {
							}.exportDb(pTarget, pExport, proceedListener);
						}
					};
				}

				@Override
				protected void failed() {
					super.failed();
					pExport.setMessages(getMessage());
					exports.remove(pExport.getExportId());
					int remaining = exports.size();
					proceedListener.onProceed(PHASE.FAILS_ALL, (double) remaining, taskCount,
							String.format(FORMATED_EXPORT_MESSAGE1, taskCount, taskCount - remaining, getMessage()));
				}

				@Override
				protected void succeeded() {
					super.succeeded();
					IResult<IExport> res = this.getValue();
					pExport.setMessages(res.getMessage());
					pExport.setEndTime(DateTime.dbNow());
					exports.remove(pExport.getExportId());
					int remaining = exports.size();
					proceedListener.onProceed(PHASE.ENDS_ALL, (double) remaining, taskCount,
							String.format(FORMATED_EXPORT_MESSAGE1, taskCount, taskCount - remaining, getMessage()));
				}
			};
			exports.put(pExport.getExportId(), export);
			export.start();
		}
	}

	public void cancelExport(List<IExport> list) {
		if (!BaseConstants.isEmpty(list)) {
			for (IExport de : list) {
				Service<IResult<IExport>> s = exports.get(de.getExportId());
				if (s != null && s.isRunning())
					s.cancel();
			}
		}

	}
}
