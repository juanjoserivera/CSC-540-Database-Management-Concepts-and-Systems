package util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Juan Jose Rivera
 *
 */
public class DateFormatUtil {

	public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM-yyyy");
	public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM-yyyy hh:mm");

	public static String formatDate(Date date) {
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

			return formatter.format(date);
		} else {
			return "N/A";
		}
	}
	
	public static String formatDateTime(Timestamp date) {
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm");

			return formatter.format(date);
		} else {
			return "N/A";
		}
	}
	
	public static String formatDateTime(LocalDateTime date) {
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy hh:mm");

			return formatter.format(date);
		} else {
			return "N/A";
		}
	}

	public static String formatStringDate(String date) {

		if (date != null) {

			return formatDate(Date.valueOf(date));
		} else {
			return "N/A";

		}

	}
	
	public static Date getDateFromString(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date date = sdf.parse(dateString);
		return new java.sql.Date(date.getTime());  

	}

}
