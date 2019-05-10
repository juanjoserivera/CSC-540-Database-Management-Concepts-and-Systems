package app.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validators {
	public final static String DATE_FORMAT = "MM-dd-yyyy";

	public static boolean isDateValid(String date) {
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
