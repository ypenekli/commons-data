package com.yp.core.tools;

import java.text.Format;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.yp.core.log.MyLogger;

import javafx.util.StringConverter;

public class IntegerStringConverter extends StringConverter<Integer> {

	private Format format;

	public IntegerStringConverter(Format pFormat) {
		super();
		format = pFormat;
	}

	@Override
	public Integer fromString(String value) {
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		try {
			return Integer.valueOf(format.parseObject(value).toString());
		} catch (NumberFormatException | ParseException h) {
			Logger.getLogger(MyLogger.NAME).log(Level.SEVERE, h.getMessage(), h);
		} 
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String toString(Integer value) {
		// If the specified value is null, return a zero-length String
		if (value == null) {
			return "";
		}

		return format.format(value);
	}
}