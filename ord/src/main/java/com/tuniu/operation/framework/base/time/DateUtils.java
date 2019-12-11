package com.tuniu.operation.framework.base.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具类
 * 
 * @author fangzhongwei
 * 
 */
public class DateUtils {

    private static final long MILLIS_PER_SECOND = 1000L;
    private static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    private static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
    private static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    public static Date add(Date date, int field, int value) {
        if (null == date) {
            throw new IllegalArgumentException("The date must not be null.");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, value);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int value) {
        return add(date, Calendar.YEAR, value);
    }

    public static Date addMonth(Date date, int value) {
        return add(date, Calendar.MONTH, value);
    }

    public static Date addDay(Date date, int value) {
        return add(date, Calendar.DAY_OF_MONTH, value);
    }

    public static Date addHour(Date date, int value) {
        return add(date, Calendar.HOUR_OF_DAY, value);
    }

    public static Date addMinute(Date date, int value) {
        return add(date, Calendar.MINUTE, value);
    }

    public static Date addSecond(Date date, int value) {
        return add(date, Calendar.SECOND, value);
    }

    public static Date addMillisecond(Date date, int value) {
        return add(date, Calendar.MILLISECOND, value);
    }

    public static Date set(Date date, int field, int value) {
        if (null == date) {
            throw new IllegalArgumentException("The date must not be null.");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(field, value);
        return calendar.getTime();
    }

    public static Date setYear(Date date, int value) {
        return set(date, Calendar.YEAR, value);
    }

    public static Date setMonth(Date date, int value) {
        return set(date, Calendar.MONTH, value);
    }

    public static Date setDay(Date date, int value) {
        return set(date, Calendar.DAY_OF_MONTH, value);
    }

    public static Date setHour(Date date, int value) {
        return set(date, Calendar.HOUR_OF_DAY, value);
    }

    public static Date setMinute(Date date, int value) {
        return set(date, Calendar.MINUTE, value);
    }

    public static Date setSecond(Date date, int value) {
        return set(date, Calendar.SECOND, value);
    }

    public static Date setMillisecond(Date date, int value) {
        return set(date, Calendar.MILLISECOND, value);
    }

    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date now() {
        return Calendar.getInstance().getTime();
    }

    public static String getDate() {
        return DateFormatUtils.formatDate(Calendar.getInstance().getTime());
    }

    public static String getDatetime() {
        return DateFormatUtils.formatDatetime(Calendar.getInstance().getTime());
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            throw new IllegalArgumentException("The date must not be null.");
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return isSameDay(calendar1, calendar2);
    }

    public static boolean isSameDay(Calendar calendar1, Calendar calendar2) {
        if (null == calendar1 || null == calendar2) {
            throw new IllegalArgumentException("The date must not be null.");
        }
        return (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR))
                && (calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))
                && (calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH));
    }

    public static Date addTwo(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            throw new IllegalArgumentException("The date must not be null.");
        }
        return new Date(date1.getTime() + date2.getTime());
    }

    public static int minusYear(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            throw new IllegalArgumentException("The date must not be null.");
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);
    }

    public static int minusMonth(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            throw new IllegalArgumentException("The date must not be null.");
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return minusYear(date1, date2) * 12 + calendar1.get(Calendar.MONTH) - calendar2.get(Calendar.MONTH);
    }

    public static int minusDay(Date date1, Date date2) {
        return (int) (minusMillisecond(date1, date2) / MILLIS_PER_DAY);
    }

    public static int minusHour(Date date1, Date date2) {
        return (int) (minusMillisecond(date1, date2) / MILLIS_PER_HOUR);
    }

    public static int minusMinute(Date date1, Date date2) {
        return (int) (minusMillisecond(date1, date2) / MILLIS_PER_MINUTE);
    }

    public static long minusSecond(Date date1, Date date2) {
        return minusMillisecond(date1, date2) / MILLIS_PER_SECOND;
    }

    public static long minusMillisecond(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            throw new IllegalArgumentException("The date must not be null.");
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
    }
    /**
     * 
     * Description:date类型转为string类型 <br>
     * 
     * @author hujin<br>
     * @taskId <br>
     * @param date
     * @return <br>
     */
    public static String dateTimeToString(Date date) {
        return formatDate("0000-00-00 00:00:00", "yyyy-MM-dd HH:mm:ss", date);
    }

    /**
     * 
     * Description:date类型转为string类型 <br>
     * 
     * @author hujin<br>
     * @taskId <br>
     * @param date
     * @return <br>
     */
    public static String dateToString(Date date) {
        return formatDate("0000-00-00", "yyyy-MM-dd", date);
    }
    
    public static Date stringToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }

    public static String dateTimesToString(Date date) {
        return formatDate("0000-00-00 00:00:00.000", "yyyy-MM-dd HH:mm:ss.SSS", date);
    }
    private static String formatDate(String defaultTime, String pattern, Date date) {
        String result = defaultTime;
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            result = sdf.format(date);
        }
        return result;
    }
    
    /**
     * 计算两个日期之间相差的天数
     * 
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = Math.abs((time2 - time1)) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的天数 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }
}
