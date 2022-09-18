package br.com.zitrus.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
public class DateUtil {

	public static String DD_MM_YYYY = "dd/MM/yyyy";

	public static Date getDateFromString(String date, String dateFormat) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDateString(Date date, String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(date);
	}
}