package com.yp.core.tools;

import java.text.Format;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.yp.core.log.MyLogger;

import javafx.util.StringConverter;

public class DoubleStringConverter extends StringConverter<Double> {

	private Format format;

	public DoubleStringConverter(Format pFormat) {
		super();
		format = pFormat;
	}

	@Override
	public Double fromString(String value) {
		// If the specified value is null or zero-length, return null
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		try {
			return Double.valueOf(format.parseObject(value).toString());
		} catch (NumberFormatException h) {
			Logger.getLogger(MyLogger.NAME).log(Level.SEVERE, h.getMessage(), h);
		} catch (ParseException h) {
			Logger.getLogger(MyLogger.NAME).log(Level.SEVERE, h.getMessage(), h);
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String toString(Double value) {
		// If the specified value is null, return a zero-length String
		if (value == null) {
			return "";
		}

		return format.format(value);
	}
}