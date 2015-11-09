package mc.webservice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {
	
	private static ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> ymdFormatter = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> y2mdFormatter = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> ymdhmsaFormatter = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> ymdhmsFormatter = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> ymdhmsMsFormatter = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> ymdhmFormatter = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> y2mdhmFormatter = new ThreadLocal<SimpleDateFormat>();	
	private static ThreadLocal<SimpleDateFormat> ymdhmsFormatter1 = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> standardFormatter = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> hmsFormatter = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> ymFormatter = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> ymNoSpaceFormatter = new ThreadLocal<SimpleDateFormat>();
	private static ThreadLocal<SimpleDateFormat> fullNoSpaceFormatter = new ThreadLocal<SimpleDateFormat>();	
	
	public static final String COMMON_FORMAT_IN_DB = "YYYY-MM-DD HH24:MI:SS";
	public static final String COMMON_FORMAT_IN_JAVA = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date) {
		if (date == null) {
			return null;
		}
		return getFormatter().format(date);
	}
	
	public static Date toDate(String input) throws ParseException {
		if (StringUtil.isEmpty(input)) {
			return null;
		}		
		return getFormatter().parse(input);
	}
	
	public static Date toDate(String date, String time) throws ParseException {
		return getFormatter().parse(date + " " + time);
	}
	
	public static Date toYmdhmsDate(String input) throws ParseException {
		if (StringUtil.isEmpty(input)) {
			return null;
		}
		return getYmdhmsFormatter().parse(input);
	}
	
	public static Date toYmdhmDate(String input) throws ParseException {
		if (StringUtil.isEmpty(input)) {
			return null;
		}
		return getYmdhmFormatter().parse(input);
	}	
	
	public static Date toYmdhmsDate1(String input) throws ParseException {
		if (StringUtil.isEmpty(input)) {
			return null;
		}
		return getYmdhmsFormatter1().parse(input);
	}
	
	public static String formatYmd(Date date) {
		if (date == null) {
			return null;
		}
		return getYmdFormatter().format(date);
	}
	
	public static String formatHms(Date date) {
		if (date == null) {
			return null;
		}
		return getHmsFormatter().format(date);
	}	
	
	public static String formatYmdhmsa(Date date) {
		if (date == null) {
			return null;
		}
		return getYmdhmsaFormatter().format(date);
	}
	
	public static String formatYmdhms(Date date) {
		if (date == null) {
			return null;
		}
		return getYmdhmsFormatter().format(date);
	}
	
	public static String formatYmdhmsMs(Date date) {
		if (date == null) {
			return null;
		}
		return getYmdhmsMsFormatter().format(date);
	}	
	
	public static String formatYmdhm(Date date) {
		if (date == null) {
			return null;
		}
		return getYmdhmFormatter().format(date);
	}	
	
	public static String formatY2mdhm(Date date) {
		if (date == null) {
			return null;
		}
		return getY2mdhmFormatter().format(date);
	}	
	
	public static String formatY2md(Date date) {
		if (date == null) {
			return null;
		}
		return getY2mdFormatter().format(date);
	}		
	
	public static String formatYmdhms1(Date date) {
		if (date == null) {
			return null;
		}
		return getYmdhmsFormatter1().format(date);
	}
	
	public static String formatYm(Date date) {
		if (date == null) {
			return null;
		}
		return getYmFormatter().format(date);
	}
	
	public static String formatYmNoSpace(Date date) {
		if (date == null) {
			return null;
		}
		return getYmNoSpaceFormatter().format(date);
	}
	
	public static String formatFullNoSpace(Date date) {
		if (date == null) {
			return null;
		}
		return getFullNoSpaceFormatter().format(date);
	}	
	
	public static Date toYmdDate(String input) throws ParseException {
		if (StringUtil.isEmpty(input)) {
			return null;
		}		
		return getYmdFormatter().parse(input);
	}	
	
	/**
	 * 
	 * @param date
	 * @return String like '2012-04-27T17:40:33.328'
	 */
	public static String formatStandard(Date date) {
		if (date == null) {
			return null;
		}
		return getStandardFormatter().format(date);
	}
	
	private static SimpleDateFormat getFormatter() {
		SimpleDateFormat format = formatter.get();
		if (format == null) {
			format = new SimpleDateFormat(COMMON_FORMAT_IN_JAVA);
			formatter.set(format);
		}
		return format;
	}
	
	private static SimpleDateFormat getYmdFormatter() {
		SimpleDateFormat format = ymdFormatter.get();
		if (format == null) {
			format = new SimpleDateFormat("yyyy-MM-dd");
			ymdFormatter.set(format);
		}
		return format;
	}
	
	private static SimpleDateFormat getStandardFormatter() {
		SimpleDateFormat format = standardFormatter.get(); 
		if (format == null) {
			format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			standardFormatter.set(format);
		}
		return format;
	}
	
	public static Date toYmdhmsaDate(String input) throws ParseException {
		if (StringUtil.isEmpty(input)) {
			return null;
		}		
		return getYmdhmsaFormatter().parse(input);
	}
	
	private static SimpleDateFormat getYmdhmsaFormatter() {
		SimpleDateFormat format = ymdhmsaFormatter.get();
		if (format == null) {
			format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a", Locale.ENGLISH);
			ymdhmsaFormatter.set(format);
		}
		return format;
	}
	
	private static SimpleDateFormat getYmdhmsFormatter() {
		SimpleDateFormat format = ymdhmsFormatter.get();
		if (format == null) {
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
			ymdhmsFormatter.set(format);
		}
		return format;
	}
	
	private static SimpleDateFormat getYmdhmsMsFormatter() {
		SimpleDateFormat format = ymdhmsMsFormatter.get();
		if (format == null) {
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
			ymdhmsMsFormatter.set(format);
		}
		return format;
	}	
	
	private static SimpleDateFormat getYmdhmFormatter() {
		SimpleDateFormat format = ymdhmFormatter.get();
		if (format == null) {
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
			ymdhmFormatter.set(format);
		}
		return format;
	}
	
	private static SimpleDateFormat getY2mdhmFormatter() {
		SimpleDateFormat format = y2mdhmFormatter.get();
		if (format == null) {
			format = new SimpleDateFormat("yy-MM-dd HH:mm", Locale.ENGLISH);
			y2mdhmFormatter.set(format);
		}
		return format;
	}	
	
	private static SimpleDateFormat getY2mdFormatter() {
		SimpleDateFormat format = y2mdFormatter.get();
		if (format == null) {
			format = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH);
			y2mdFormatter.set(format);
		}
		return format;
	}
	
	private static SimpleDateFormat getYmdhmsFormatter1() {	//2012/4/10  18:01:20
		SimpleDateFormat format = ymdhmsFormatter1.get();
		if (format == null) {
			format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);
			ymdhmsFormatter1.set(format);
		}
		return format;
	}
	
	private static SimpleDateFormat getHmsFormatter() {
		SimpleDateFormat format = hmsFormatter.get();
		if (format == null) {
			format = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
			hmsFormatter.set(format);
		}
		return format;	
	}
	
	private static SimpleDateFormat getYmFormatter() {
		SimpleDateFormat format = ymFormatter.get();
		if (format == null) {
			format = new SimpleDateFormat("MM yyyy", Locale.ENGLISH);
			hmsFormatter.set(format);
		}
		return format;
	}
	
	private static SimpleDateFormat getYmNoSpaceFormatter() {
		SimpleDateFormat format = ymNoSpaceFormatter.get();
		if (format == null) {
			format = new SimpleDateFormat("yyyyMM", Locale.ENGLISH);
			hmsFormatter.set(format);
		}
		return format;
	}
	
	private static SimpleDateFormat getFullNoSpaceFormatter() {
		SimpleDateFormat format = fullNoSpaceFormatter.get();
		if (format == null) {
			format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
			fullNoSpaceFormatter.set(format);
		}
		return format;
	}
	
	/**
	 * 
	 * @param date
	 * @param timeStr Format: "00:00", "01:25", "17:35", etc.
	 * @return
	 * @throws ParseException 
	 */
	public static Date combineDateTime(Date date, String timeStr) throws ParseException {
		if (date == null) {
			return null;
		}
		if (StringUtil.isEmpty(timeStr)) {
			return date;
		}
		timeStr = filterStandardTime(timeStr);		
		String dateStr = formatYmd(date);
		StringBuilder fullStr = new StringBuilder(dateStr).append(" ").append(timeStr);
		if (timeStr.length() == 5) {
			fullStr.append(":00");
		}
		return getFormatter().parse(fullStr.toString());
	}

	public static String filterStandardTime(String standardTime) {
		if (StringUtil.isEmpty(standardTime)) {
			return null;
		}
		String[] p = standardTime.split("T");
		if (p.length == 1) {
			return p[0];
		}
		return p[1];
	}

}
