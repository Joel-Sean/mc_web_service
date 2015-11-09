package mc.webservice.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.regex.Pattern;


import org.apache.log4j.Logger;


import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.util.concurrent.TimeUnit;
public class CommonUtil {
	private static final Logger logger = Logger.getLogger(CommonUtil.class);
	public static final Pattern NUM_PATTERN = Pattern.compile("[0-9]*");	
	
	public static boolean greaterThanZero(BigDecimal d) {
		return d != null && d.compareTo(BigDecimal.ZERO) > 0;
	}
	
	public static boolean equals(Object object1, Object object2) {
		if (object1 == null || object2 == null) {
			return false;
		}
		if (object1 == object2) {
			return true;
		}
		return object1.equals(object2);
	}
	
	public static Integer getDate(Date date) {
		return getDate(date, null);
	}

	public static Integer getDate(Date date, TimeZone tz) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (tz != null) {
			cal.setTimeZone(tz);
		}
		return new Integer(cal.get(Calendar.YEAR) * 10000
				+ (cal.get(Calendar.MONTH) + 1) * 100
				+ cal.get(Calendar.DAY_OF_MONTH));
	}

	public static Date getDate(Integer date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.clear();
		int year = date / 10000;
		int month = (date - year * 10000) / 100;
		int day = date - year * 10000 - month * 100;
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, day);

		return cal.getTime();
	}	
	
	public static Date convertDate(String dateStr, boolean isEndDate) {
		Date date;
		try {
			date = DateFormatter.toYmdDate(dateStr);
			if (isEndDate) {
				date = processEndDate(date);
			} else {
				date = processStartDate(date);
			}
		} catch (Exception e) {
			return null;
		}	
		return date;
	}	
	
	public static Date processStartDate(Date startDate) throws ParseException {
		if (startDate == null) {
			return null;
		}
		return DateFormatter.toDate(DateFormatter.formatYmd(startDate) +" 00:00:00");
	}
	public static Date processEndDate(Date endDate) throws ParseException {
		if (endDate == null) {
			return null;
		}
		return DateFormatter.toDate(DateFormatter.formatYmd(endDate) +" 23:59:59");
	}	
	public static Date processDate(Date date, String time) throws ParseException {
		if (date == null) {
			return null;
		}
		return DateFormatter.toDate(DateFormatter.formatYmd(date) + " " + time);
	}
	public static Date processDate(String date, String time) throws ParseException {
		if (StringUtil.isEmpty(date)) {
			return null;
		}
		return DateFormatter.toDate(date + " " + time);
	}
	
	public static Date getCurrentWeekStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, cal.getActualMinimum(Calendar.DAY_OF_WEEK) + 1);
		return cal.getTime();
	}
	
	/**
	 * @param date
	 * @param difference
	 *            Positive or negative number
	 * @return
	 */
	public static Date xDaysDiff(Date date, int difference) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)
				+ difference);
		return cal.getTime();
	}

	public static Integer xDaysDiffAsInt(Date date, int difference) {
		if (date == null) {
			return null;
		}
		return getDate(xDaysDiff(date, difference));
	}

	public static boolean sameDay(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		
		return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
	}
	
	public static int daysDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return 0;
		}
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.setTime(startDate);
		end.setTime(endDate);
		clearTime(start);
		clearTime(end);
		
		long milliseconds1 = start.getTimeInMillis();
		long milliseconds2 = end.getTimeInMillis();
		long diff = milliseconds2 - milliseconds1;
//		long diffSeconds = diff / 1000;
//		long diffMinutes = diff / (60 * 1000);
//		long diffHours = diff / (60 * 60 * 1000);
		long diffDays = diff / (24 * 60 * 60 * 1000);		
		
		return (int)diffDays;
	}
	
	public static void clearTime(Calendar cal) {
		if (cal == null) {
			return;
		}
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}
	
	public static Date getDateBegin(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getDateEnd(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.AM_PM, Calendar.PM);
		cal.set(Calendar.HOUR, 11);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static String pwdEncrypt(String input) {
		if (input == null) {
			return null;
		}
		//return md5(input);
		return sha1(input);
	}
	
	public static String sha1(String input) {
		if (input == null) {
			return null;
		}		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			throw new SxException(-110005,"this can't happen: " + e.getMessage());
		} 
		byte[] hash = md.digest(input.getBytes());		
	    Formatter formatter = new Formatter();
	    for (byte b : hash) {
	        formatter.format("%02x", b);
	    }
	    return formatter.toString();		
	}

	public static String md5(String input) {
		if (input == null) {
			return null;
		}		
		MessageDigest digest;
		try {
			digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(input.getBytes());
			byte[] hash = digest.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < hash.length; ++i) {
				sb.append(Integer.toHexString((hash[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
			logger.error("md5 error.", e);
			return "";
		}		
	}
	
	public static int guessResultLength(int start, int limit, int length) {
		if (length <= limit) {
			return start + length;
		} else {
			return start + limit + 1;
		}
	}
	
	public static Date diffDate(Date date, int days) {
		long now = date == null ? System.currentTimeMillis() : date.getTime();
		return new Date(now + ((long)24) * 60 * 60 * 1000 * days);
	}
	
	public static Date diffMinute(Date date, int minutes) {
		long now = date == null ? System.currentTimeMillis() : date.getTime();
		return new Date(now + ((long)60) * 1000 * minutes);
	}
	
	public static Date diffHour(Date date, int hours) {
		return diffMinute(date, hours * 60);
	}
	
	public static Date yesterday(Date date) {
		return diffDate(date, -1);
	}

	public static ZipFile zip(File zip, File fileToAdd, String password)
			throws ZipException {

		// zipPath is absolute path of zipped file
		ZipFile zipFile = new ZipFile(zip);

		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		if (!StringUtil.isEmpty(password)) {
			parameters.setEncryptFiles(true);
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
			parameters.setPassword(password);
		}
		zipFile.addFile(fileToAdd, parameters);
		return zipFile;
	}

	public static void unzip(File zip, String extractPath, String password)
			throws ZipException {
		ZipFile zipFile = new ZipFile(zip);
		if (extractPath == null) {
			extractPath = zip.getParent();
		}
		if (zipFile.isEncrypted()) {
			zipFile.setPassword(password);
		}
		zipFile.extractAll(extractPath);
	}

	private static final SimpleDateFormat BUSS_DATE_FORMAT = new SimpleDateFormat(
			"yyyyMMdd hh:mm:ss");
	public static Date getBusinessDate(Date date, String businessTime) {
		StringBuffer buffer = new StringBuffer(getDate(date).toString());
		buffer.append(" ");
		buffer.append(businessTime);
		synchronized (BUSS_DATE_FORMAT) {
			try {
				return BUSS_DATE_FORMAT.parse(buffer.toString());
			} catch (ParseException e) {
				return null;
			}
		}
	}
	
	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm:ss");
	public static String getTime(Date date) {
		synchronized (TIME_FORMAT) {
			return TIME_FORMAT.format(date);
		}
	}

	public static BigDecimal getMin(BigDecimal v1, BigDecimal v2) {
		if (v1 == null) {
			return v2;
		}
		if (v2 == null) {
			return v1;
		}
		return v1.compareTo(v2) < 0 ? v1 : v2;
	}
	
	public static BigDecimal getMax(BigDecimal v1, BigDecimal v2) {
		if (v1 == null) {
			return v2;
		}
		if (v2 == null) {
			return v1;
		}
		return v1.compareTo(v2) > 0 ? v1 : v2;
	}
	
	public static String getDateFromMsec(Date startTime, Date endTime) {
		if (startTime == null || endTime == null) {
			return null;
		}
		long diffMSec = endTime.getTime() - startTime.getTime();
	    int left = 0;
	    int ss = 0;
	    int mm = 0;
	    int hh = 0;
	    left = (int) (diffMSec / 1000);
	    ss = left % 60;
	    left = (int) left / 60;
	    if (left > 0) {
	        mm = left % 60;
	        hh = (int) left / 60;
	    }
	    return hh + ":" + (mm < 10 ? "0" + mm : mm) + ":" + (ss < 10 ? "0" + ss : ss);
	}
	
	public static Properties reloadPropInContainerClassPath(String filename) {
		Properties p = new Properties();
		InputStream in = null;
		try {
			String filepath = Thread.currentThread().getContextClassLoader().getResource(filename).getPath();
			in = new FileInputStream(filepath);
			BufferedReader bf = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			p.load(bf);
		} catch (IOException e) {
			throw new SxException(-110008, "Error in getting properties: " + e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("Error when trying to close input stream:" + filename, e);
				}
			}
		}
		return p;
	}
	
	public static Properties getPropInClassPath(String filename) {
		Properties p = new Properties();
		try {
			p.load(CommonUtil.class.getClassLoader()
					.getResourceAsStream(filename));
		} catch (IOException e) {
			throw new SxException(-110008,"Error in getting properties: " + e.getMessage());
		}
		return p;
	}
	
	public static Properties getPropInClassPathUTF8(String filename) {
		Properties p = new Properties();
		
		try {
			InputStream inputStream = CommonUtil.class.getClassLoader()
					.getResourceAsStream(filename);
			BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
			p.load(bf);
		} catch (IOException e) {
			throw new SxException("Error in getting properties", e);
		}
		return p;
	}
	
	public static Properties getPropInExactClassPath(String filename, @SuppressWarnings("rawtypes") Class clazz) {
		Properties p = new Properties();
		try {
			String filepath = clazz.getResource(filename).getPath();
			p.load(new FileInputStream(filepath));
		} catch (IOException e) {
			throw new SxException(-110008,"Error in getting properties: " + e.getMessage());
		}
		return p;
	}	
	
	
	private static final BigDecimal _ONE_HUNDREAD = new BigDecimal(100);
	public static BigDecimal meanIncreaseRate(BigDecimal start, BigDecimal end, int n) {
		if (n <= 0 || start.equals(BigDecimal.ZERO)) {
			return null;
		}
		BigDecimal rate = end.divide(start, 4, RoundingMode.HALF_UP);
		double x = java.lang.StrictMath.pow(rate.doubleValue(), 1.0 / n);
		return new BigDecimal(x - 1.0).multiply(_ONE_HUNDREAD).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public static String toMmSs(long ms) {
		long sec = TimeUnit.MILLISECONDS.toSeconds(ms);
		int mm = (int)TimeUnit.MILLISECONDS.toMinutes(ms);
		int ss = (int) sec % 60;
		return mm + "m " + ss + "s";
	}
	
	public static int secsDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return 0;
		}
		
		long diffSeconds = (endDate.getTime() - startDate.getTime())/1000;		
		return (int)diffSeconds;
	}
	
	public static <T> List<List<T>> splitList(List<T> list, int size) {
		if (list == null) {
			return null;
		}
		if (size <= 0) {
			return null;
		}
		List<List<T>> result = CollectionUtil.createGenericList();
    	for (int i = 0; ; i ++) {
    		boolean toBreak = false;
    		int start = i * size;
    		int end = (i+1) * size - 1;
    		if (end >= list.size() - 1) {
    			end = list.size() - 1;
    			toBreak = true;
    		}
    		List<T> subList = list.subList(start, end + 1);
    		result.add(subList);
    		if (toBreak) {
    			break;
    		}
    	}		
		return result;
	}
	
	public static Date getSundayByDate(Date seDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(seDate);		
		int dayOfTheWeek = c.get( Calendar.DAY_OF_WEEK );
		c.add( Calendar.DAY_OF_WEEK, Calendar.SUNDAY - dayOfTheWeek );
		return c.getTime();
	}

	public static boolean isTrue(Object o) {
		if (o instanceof Boolean) {
			if (((Boolean) o).booleanValue()) {
				return true;
			}
		}
		return false;
	}
	
	public static Date getFirstDayOfMonthByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	public static Date getLastDayOfMonthByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	public static Date getPreviousMonthByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
}
