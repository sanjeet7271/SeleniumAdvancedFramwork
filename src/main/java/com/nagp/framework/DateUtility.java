package com.nagp.framework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author sanjeet.pandit
 *
 */
public class DateUtility {

	private String timeFormat = "";

	/**
	 * @author sanjeet.pandit
	 * @return
	 */
	public String getCurrentTime() {
		timeFormat = "dd/MM/yyyy hh:mm:ss a";
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}

	/**
	 * @author sanjeet.pandit
	 * @param timeFormat
	 * @return
	 */
	public String getCurrentTime(String timeFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}

	/**
	 * @author sanjeet.pandit
	 * @param timeFormat
	 * @return
	 */
	public String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hhmmss a", Locale.ENGLISH);
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}

	/**
	 * @author sanjeet.pandit
	 * @param dateStart
	 * @param dateStop
	 * @return
	 */
	public long getDateDifference(String dateStart, String dateStop) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a", Locale.ENGLISH);
		long diffInMillies = 0;
		try {
			diffInMillies = Math.abs(sdf.parse(dateStop).getTime() - sdf.parse(dateStart).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	/**
	 * @author sanjeetpandit
	 * @param dateStart
	 * @param dateStop
	 * @return
	 */
	public String getElapsedTime(String dateStart, String dateStop) {
		long sec = getDateDifference(dateStart, dateStop);
		long min = TimeUnit.MINUTES.convert(sec, TimeUnit.SECONDS);
		if (min <= 0) {
			return String.valueOf(sec) + " Secs";
		}
		return String.valueOf(min) + "Mins";
	}
}
