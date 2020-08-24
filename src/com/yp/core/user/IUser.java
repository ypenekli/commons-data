package com.yp.core.user;

import java.util.Date;

import com.yp.core.entity.IDataEntity;

public interface IUser extends IDataEntity {

	Integer getId();

	String getName();

	String getSurname();

	String getEmail();

	String getMobilePhoneNu();

	String getFullName();

	String getPassword();

	boolean isStatusActive();

	void setId(Integer pId);

	void setPassword(String pPassword);

	void setStatusActive(Boolean pStatus);

	void setLoginErrorCount(Integer pLoginErrorCount);

	void setCheckinDate(Date pCheckinDate);

	void setCheckoutDate(Date pCheckoutDate);

}
