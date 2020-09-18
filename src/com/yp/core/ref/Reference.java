package com.yp.core.ref;

import com.yp.core.entity.DataEntity;

public class Reference<T> extends DataEntity implements IReference<T> {

	public static final transient String KEY = "key";
	public static final transient String VALUE = "value";
	private static final String descriptionFieldName = "description";

	private static final long serialVersionUID = 7652554245250573812L;
	private String keyFieldName;
	private String valueFieldName;
	private String toStringFieldName;

	public Reference(String pKeyFieldName, T pKeyFieldValue, String pValueFieldName,
			String pValueFieldValue) {
		super();
		keyFieldName = pKeyFieldName;
		valueFieldName = pValueFieldName;
		toStringFieldName = valueFieldName;
		setKey(pKeyFieldValue);
		setValue(pValueFieldValue);
	}

	public Reference(T pKeyFieldValue, String pValueFieldValue) {
		this(KEY, pKeyFieldValue, VALUE, pValueFieldValue);
	}

	public Reference(T pKeyFieldValue) {
		this(KEY, pKeyFieldValue, VALUE, pKeyFieldValue.toString());
	}

	public Reference(T pKeyFieldValue, String pVlueFieldValue, String pExtra) {
		this(pKeyFieldValue, pVlueFieldValue);
		set(descriptionFieldName, pExtra);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getKey() {
		return (T) get(keyFieldName);
	}

	@Override
	public void setKey(T pKey) {
		set(keyFieldName, pKey);
	}

	@Override
	public String getValue() {
		return (String) get(valueFieldName);
	}

	@Override
	public void setValue(String pValue) {
		set(valueFieldName, pValue);
	}

	@Override
	public String getDescription() {
		return (String) get(descriptionFieldName);
	}

	@Override
	public void setDescription(String pExtra) {
		set(descriptionFieldName, pExtra);
	}

	public void setToStringField(String pToStringField) {
		toStringFieldName = pToStringField;
	}

	@Override
	public String toString() {
		if (!isNull(toStringFieldName))
			return (String) get(toStringFieldName);
		else
			return super.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object pObj) {
		if (pObj instanceof Reference) {
			if (!isNull(keyFieldName))
				return getKey().equals(((Reference<T>) pObj).getKey());
		} else if (pObj != null) {
			return get(keyFieldName).equals(pObj);
		}
		return false;
	}

}
