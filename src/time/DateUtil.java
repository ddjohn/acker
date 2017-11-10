package time;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date daysAgo(int calendarType, int calendarValue) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendarType, -calendarValue);
		return calendar.getTime();
	}

	public static Date today() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();		
	}
}
