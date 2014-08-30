package com.whr.dms.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
	
	public static Date getTodayDate() {
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date today = new Date();
			Date todayWithZeroTime =formatter.parse(formatter.format(today));
			return todayWithZeroTime;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Date getTomorrowDate() {
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 1);
			
			Date tomorrow = c.getTime();
			Date date =formatter.parse(formatter.format(tomorrow));
			return date;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
