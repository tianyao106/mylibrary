package com.intelink.express.mylibrary.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class DateUtils {
	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private static final String TIME_PATTERN = "HH:mm:ss";
	
	public static String format(Date date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}
	
	public static String format(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
		return dateFormat.format(date);
	}	
	
	public static String getDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
		return dateFormat.format(date);
	}
	
	public static String getTime(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN);
		return dateFormat.format(date);
	}
	
	public static Date parseDate(String string) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
		try {
			return dateFormat.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
}
