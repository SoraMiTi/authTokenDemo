package com.example.authTokenDemo.util;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author luwl
 * @version [1.0.0, 2020/7/28]
 **/
public final class DateUtil {
    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    public static final String SHORT_DATE_FORMAT_PATTERN = "yyyyMMdd";
    public static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT_PATTERN = "HH:mm:ss";
    public static final String SPECIFIC_DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_DAY_MIN_TIME = " 00:00:00";
    public static final String DATE_DAY_MAX_TIME = " 23:59:59";
    public static final char[] UPPER_NUMBER = "〇一二三四五六七八九十".toCharArray();
    /**
     * 一小时的秒数 = 60*60
     */
    public static final long ONE_HOUR_SECOND = 3600;

    private DateUtil() {

    }

    /**
     * 获取指定日期   yyyy-MM-dd HH:mm:ss格式
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String getDateFormat(Date date) {
        return getDateFormat(date, SPECIFIC_DATE_TIME_FORMAT_PATTERN);
    }

    /**
     * 获得当前日期   yyyy-MM-dd HH:mm:ss格式
     *
     * @return {@link String}
     */
    public static String getCurrentDateFormat() {
        return getDateFormat(new Date(), SPECIFIC_DATE_TIME_FORMAT_PATTERN);
    }

    /**
     * 获取指定日期   yyyyMMdd格式
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String getDateShortFormat(Date date) {
        return getDateFormat(date, SHORT_DATE_FORMAT_PATTERN);
    }

    /**
     * 获取指定日期   yyyy-MM-dd格式
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String getDateDefaultFormat(Date date) {
        return getDateFormat(date, DEFAULT_DATE_FORMAT_PATTERN);
    }

    /**
     * 获得当前日期    yyyyMMdd格式
     *
     * @return {@link String}
     */
    public static String getCurrentDateShortFormat() {
        return getDateFormat(new Date(), SHORT_DATE_FORMAT_PATTERN);
    }

    /**
     * 获得当前日期    yyyy-MM-dd格式
     *
     * @return {@link String}
     */
    public static String getCurrentDateDefaultFormat() {
        return getDateFormat(new Date(), DEFAULT_DATE_FORMAT_PATTERN);
    }

    /**
     * 获得当前日期   指定格式
     *
     * @param format 格式
     * @return {@link String}
     */
    public static String getCurrentDateFormat(String format) {
        return getDateFormat(new Date(), format);
    }

    /**
     * 获取指定日期   指定格式
     *
     * @param date   日期
     * @param format 格式
     * @return {@link String}
     */
    public static String getDateFormat(Date date, String format) {
        return new DateTime(date).toString(format);
    }

    /**
     * 解析日期     以"yyyy-MM-dd"格式
     *
     * @param dateStr str日期
     * @return {@link Date}
     */
    public static Date parseDefaultDate(String dateStr) {
        return parseDate(dateStr, DEFAULT_DATE_FORMAT_PATTERN);
    }

    /**
     * 解析具体的时间
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss 类型
     * @return Date类型
     */
    public static Date parseSpecificDateTime(String dateStr) {
        return parseDate(dateStr, SPECIFIC_DATE_TIME_FORMAT_PATTERN);
    }

    /**
     * 解析日期     以指定格式
     *
     * @param dateStr str日期
     * @param format  格式
     * @return {@link Date}
     */
    public static Date parseDate(String dateStr, String format) {
        return DateTimeFormat.forPattern(format).parseDateTime(dateStr).toDate();
    }

    /**
     * 向日期增加天数
     *
     * @param date 日期
     * @param day  天数
     * @return {@link Date}
     */
    public static Date addDay(Date date, int day) {
        return new DateTime(date).plusDays(day).toDate();
    }

    /**
     * 向日期减少天数
     *
     * @param date 日期
     * @param day  天数
     * @return {@link Date}
     */
    public static Date minusDay(Date date, int day) {
        return new DateTime(date).minusDays(day).toDate();
    }

    /**
     * 向日期增加月数
     *
     * @param date  日期
     * @param month 月
     * @return {@link Date}
     */
    public static Date addMonth(Date date, int month) {
        return new DateTime(date).plusMonths(month).toDate();
    }

    /**
     * 向日期增加年数
     *
     * @param date 日期
     * @param year 年
     * @return {@link Date}
     */
    public static Date addYear(Date date, int year) {
        return new DateTime(date).plusYears(year).toDate();
    }

    /**
     * 向日期减少月数
     *
     * @param date  日期
     * @param month 月
     * @return {@link Date}
     */
    public static Date minusMonth(Date date, int month) {
        return new DateTime(date).minusMonths(month).toDate();
    }

    /**
     * 向日期增加小时
     *
     * @param date 日期
     * @param hour 小时
     * @return {@link Date}
     */
    public static Date addHour(Date date, int hour) {
        return new DateTime(date).plusHours(hour).toDate();
    }

    /**
     * 向日期减少小时
     *
     * @param date 日期
     * @param hour 小时
     * @return {@link Date}
     */
    public static Date minusHour(Date date, int hour) {
        return new DateTime(date).minusHours(hour).toDate();
    }

    /**
     * 向日期增加分钟
     *
     * @param date    日期
     * @param minutes 分钟
     * @return {@link Date}
     */
    public static Date addMinutes(Date date, int minutes) {
        return new DateTime(date).plusMinutes(minutes).toDate();
    }

    /**
     * 向日期减少分钟
     *
     * @param date    日期
     * @param minutes 分钟
     * @return {@link Date}
     */
    public static Date minusMinutes(Date date, int minutes) {
        return new DateTime(date).minusMinutes(minutes).toDate();
    }

    /**
     * 向日期增加秒
     *
     * @param date    日期
     * @param seconds 秒
     * @return {@link Date}
     */
    public static Date addSeconds(Date date, int seconds) {
        return new DateTime(date).plusSeconds(seconds).toDate();
    }

    /**
     * 向日期减少秒
     *
     * @param date    日期
     * @param seconds 秒
     * @return {@link Date}
     */
    public static Date minusSeconds(Date date, int seconds) {
        return new DateTime(date).minusSeconds(seconds).toDate();
    }

    /**
     * 在日期范围内的随机日期
     *
     * @param startStr 开始str
     * @param endStr   str结束
     * @return {@link Date}
     */
    public static Date randomRangeDate(String startStr, String endStr) {
        long startTime = new DateTime(startStr).toDate().getTime();
        long endTime = new DateTime(endStr).toDate().getTime();
        double randomDate = Math.random() * (endTime - startTime) + startTime;
        DateTime random = new DateTime(Math.round(randomDate));
        return random.toDate();
    }

    /**
     * 比较两个时间相差多少秒
     */
    public static long getDiffSeconds(Date startDate, Date endDate) {
        return Math.abs((endDate.getTime() - startDate.getTime()) / 1000);
    }

    /**
     * 比较两个时间相差多少分钟
     */
    public static long getDiffMinutes(Date startDate, Date endDate) {
        long diffSeconds = getDiffSeconds(startDate, endDate);
        return diffSeconds / 60;
    }

    /**
     * 比较两个时间相差多少天
     * 如果开始时间<结束时间 返回天数为正值
     * 如果开始时间>结束时间 返回天数为负值
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     */
    public static long getDiffDay(Date startDate, Date endDate) {
        long between = Math.abs((startDate.getTime() - endDate.getTime()) / 1000);
        long day = between / 60 / 60 / 24;
        if (startDate.after(endDate)) {
            return (long) -Math.floor(day);
        }
        return (long) Math.floor(day);
    }

    /**
     * 比较两个时间相差多少天
     * 如果开始时间<结束时间 返回天数为正值
     * 如果开始时间>结束时间 返回天数为负值
     *
     * @param startDate 开始时间
     */
    public static long getCurrentDiffDay(Date startDate) {
        return getDiffDay(startDate, new Date());
    }

    /**
     * 比较两个时间相差多少天
     * 如果开始时间<结束时间 返回天数为正值
     * 如果开始时间>结束时间 返回天数为负值
     *
     * @param startDate 开始时间
     */
    public static long getCurrentDiffMinutes(Date startDate) {
        return getDiffMinutes(startDate, new Date());
    }

    /**
     * 获取两个时间相差月份
     */
    public static int getDiffMonth(Date start, Date end) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        return (endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)) * 12
                + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
    }

    /**
     * 获取两个时间相差年份
     */
    public static int getDiffYear(Date start, Date end) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        return (endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR));
    }

    /**
     * 返回传入时间月份的第一天
     */
    public static Date firstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

    /**
     * 返回传入时间月份的最后一天
     */
    public static Date lastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

    /**
     * 判断是否为闰年
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * 计算相对于dateToCompare的年龄，长用于计算指定生日在某年的年龄
     *
     * @param birthDay      生日
     * @param dateToCompare 需要对比的日期
     * @return 年龄
     */
    public static int age(Date birthDay, Date dateToCompare) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateToCompare);

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("Birthday is after date " + getDateFormat(birthDay) + "!");
        }

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthDay);
        int age = year - cal.get(Calendar.YEAR);

        int monthBirth = cal.get(Calendar.MONTH);
        if (month == monthBirth) {
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
            if (dayOfMonth < dayOfMonthBirth) {
                // 如果生日在当月，但是未达到生日当天的日期，年龄减一
                age--;
            }
        } else if (month < monthBirth) {
            // 如果当前月份未达到生日的月份，年龄计算减一
            age--;
        }

        return age;
    }

    public static Date randomDate() {
        return randomRangeDate("1990-01-01", "2018-12-31");
    }


    public static final int DAYS_PER_WEEKEND = 2;
    public static final int WEEK_START = DateTimeConstants.MONDAY;
    public static final int WEEK_END = DateTimeConstants.FRIDAY;

    /**
     * 获取两个日期之间的工作日
     *
     * @param d1 startDate
     * @param d2 endDate
     * @return 工作日天数
     */
    public static int workdayDiff(Date d1, Date d2) {
        LocalDate start = LocalDate.fromDateFields(d1);
        LocalDate end = LocalDate.fromDateFields(d2);
        start = toWorkday(start);
        end = toWorkday(end);
        int daysBetween = Days.daysBetween(start, end).getDays();
        int weekendsBetween = Weeks.weeksBetween(start.withDayOfWeek(WEEK_START), end.withDayOfWeek(WEEK_START)).getWeeks();
        return daysBetween - (weekendsBetween * DAYS_PER_WEEKEND);
    }

    /**
     * 获取指定日期的 yyyy-MM-dd 23:59:59 格式
     *
     * @param date 日期
     * @return yyyy-MM-dd 23:59:59
     */
    public static String getDateFormatMaxTime(Date date) {
        String dateFormat = getDateFormat(date, DEFAULT_DATE_FORMAT_PATTERN);
        return dateFormat + DATE_DAY_MAX_TIME;
    }

    /**
     * 获取指定日期的 yyyy-MM-dd 00:00:00 格式
     *
     * @param date 日期
     * @return yyyy-MM-dd 00:00:00
     */
    public static String getDateFormatMinTime(Date date) {
        String dateFormat = getDateFormat(date, DEFAULT_DATE_FORMAT_PATTERN);
        return dateFormat + DATE_DAY_MIN_TIME;
    }


    /**
     * 根据小写数字格式的日期转换成大写格式的日期
     * 支持yyyy-MM-dd、yyyy/MM/dd、yyyyMMdd等格式
     *
     * @param date 日期
     * @return 二〇二〇年八月五日
     */
    public static String getUpperDate(Date date) {
        return getUpperDate(getDateShortFormat(date));
    }

    /**
     * 获取当前日期
     * 根据小写数字格式的日期转换成大写格式的日期
     * 支持yyyy-MM-dd、yyyy/MM/dd、yyyyMMdd等格式
     *
     * @return 二〇二〇年八月五日
     */
    public static String getUpperDate() {
        return getUpperDate(new Date());
    }

    /**
     * 根据小写数字格式的日期转换成大写格式的日期
     * 支持yyyy-MM-dd、yyyy/MM/dd、yyyyMMdd等格式
     *
     * @param date 字符串日期
     * @return 二〇二〇年八月五日
     */
    public static String getUpperDate(String date) {
        if (date == null) {
            return "";
        }
        //非数字的都去掉
        date = date.replaceAll("\\D", "");
        if (date.length() != 8) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {//年
            sb.append(UPPER_NUMBER[Integer.parseInt(date.substring(i, i + 1))]);
        }
        sb.append("年");//拼接年
        int month = Integer.parseInt(date.substring(4, 6));
        if (month <= 10) {
            sb.append(UPPER_NUMBER[month]);
        } else {
            sb.append("十").append(UPPER_NUMBER[month % 10]);
        }
        sb.append("月");//拼接月

        int day = Integer.parseInt(date.substring(6));
        if (day <= 10) {
            sb.append(UPPER_NUMBER[day]);
        } else if (day < 20) {
            sb.append("十").append(UPPER_NUMBER[day % 10]);
        } else {
            sb.append(UPPER_NUMBER[day / 10]).append("十");
            int tmp = day % 10;
            if (tmp != 0) {
                sb.append(UPPER_NUMBER[tmp]);
            }
        }
        sb.append("日");//拼接日
        return sb.toString();
    }


    public static LocalDate toWorkday(LocalDate d) {
        if (d.getDayOfWeek() > WEEK_END) {
            return d.plusDays(DateTimeConstants.DAYS_PER_WEEK - d.getDayOfWeek() + 1);
        }
        return d;
    }

    /**
     * 根据月份获取英文月份名称
     *
     * @param month 月份 1~12
     * @return 英文月份名称
     */
    public static String getMonthEn(int month) {
        String[] allMonths = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if (0 < month && month < 13) {
            return allMonths[month - 1];
        }
        return "";
    }

    /**
     * 根据月份获取中文月份名称
     *
     * @param month 月份 1~12
     * @return 中文月份名称
     */
    public static String getMonthZh(int month) {
        String[] allMonths = new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
        if (0 < month && month < 13) {
            return allMonths[month - 1];
        }
        return "";
    }

    /**
     * 根据月份获取中文月份名称
     *
     * @param ymd yyyy-MM-dd格式的年月日
     * @return 中文月份名称
     */
    public static String getMonthZh(String ymd) {
        int monthOfYear = DateTimeFormat.forPattern(DEFAULT_DATE_FORMAT_PATTERN).parseDateTime(ymd).getMonthOfYear();
        return getMonthZh(monthOfYear);
    }

    /**
     * 获取英文出生日期
     *
     * @param date 日期
     * @return April 1.1979
     */
    private static String getEnBirthDay(Date date) {
        DateTime dateTime = new DateTime(date);
        String monthEn = getMonthEn(dateTime.getMonthOfYear());
        return monthEn + " " + dateTime.getDayOfMonth() + "." + dateTime.getYear();
    }

    /**
     * 现在的时间在指定的时间范围内
     * <pre>
     *      DateUtil.nowInTimeRange("08:00:00", "17:00:00") -> 当前时间是不是在这个范围内
     * </pre>
     *
     * @param timeStart 时间开始  in format "hh:mm:ss"
     * @param timeEnd   时间结束  in format "hh:mm:ss"
     * @return boolean 如果时间格式不正确直接返回false
     */
    public static boolean nowInTimeRange(String timeStart, String timeEnd) {
        if (RegexUtil.isTime(timeStart) && RegexUtil.isTime(timeEnd)) {
            Date nowDate = new Date();
            String dateFormat = getDateFormat(nowDate, DEFAULT_DATE_FORMAT_PATTERN);
            Date startDate = parseSpecificDateTime(dateFormat + " " + timeStart);
            Date endDate = parseSpecificDateTime(dateFormat + " " + timeEnd);
            return nowDate.after(startDate) && nowDate.before(endDate);
        } else {
            log.error("计算现在的时间在指定的时间范围内错误！{}-{}", timeStart, timeEnd);
        }
        return false;
    }

    /**
     * 获取上月的第一天
     * if today - will 2020-09-21 14:14:14 -> 2020-09-01
     *
     * @return {@link Date}
     */
    public static Date getPreviousMonthFirstDayToDate() {
        return LocalDateTime.now().minusMonths(1).dayOfMonth().withMinimumValue().toDate();
    }

    /**
     * 获取上月的最后一天
     * if today - will 2020-09-21 14:14:14 -> 2020-09-30
     *
     * @return {@link Date}
     */
    public static Date getPreviousMonthLastDayToDate() {
        return LocalDateTime.now().minusMonths(1).dayOfMonth().withMaximumValue().toDate();
    }

    /**
     * 获取上月的第一天
     * if today - will 2020-09-21 14:14:14 -> 2020-09-01
     *
     * @return {@link String}
     */
    public static String getPreviousMonthFirstDayToString() {
        return new DateTime(getPreviousMonthFirstDayToDate()).toString(DEFAULT_DATE_FORMAT_PATTERN);
    }

    /**
     * 获取上月的最后一天
     * if today - will 2020-09-21 14:14:14 -> 2020-09-30
     *
     * @return {@link String}
     */
    public static String getPreviousMonthLastDayToString() {
        return new DateTime(getPreviousMonthLastDayToDate()).toString(DEFAULT_DATE_FORMAT_PATTERN);
    }


    /**
     * 获取当月的第一天
     * if today - will 2020-09-21 14:14:14 -> 2020-09-01
     *
     * @return {@link Date}
     */
    public static Date getCurrentMonthFirstDayToDate() {
        return LocalDateTime.now().dayOfMonth().withMinimumValue().toDate();
    }

    /**
     * 获取当月的最后一天
     * if today - will 2020-09-21 14:14:14 -> 2020-09-30
     *
     * @return {@link Date}
     */
    public static Date getCurrentMonthLastDayToDate() {
        return LocalDateTime.now().dayOfMonth().withMaximumValue().toDate();
    }

    /**
     * 获取当月的第一天
     * if today - will 2020-09-21 14:14:14 -> 2020-09-01
     *
     * @return {@link String}
     */
    public static String getCurrentMonthFirstDayToString() {
        return new DateTime(getCurrentMonthFirstDayToDate()).toString(DEFAULT_DATE_FORMAT_PATTERN);
    }

    /**
     * 获取当月的最后一天
     * if today - will 2020-09-21 14:14:14 -> 2020-09-30
     *
     * @return {@link String}
     */
    public static String getCurrentMonthLastDayToString() {
        return new DateTime(getCurrentMonthLastDayToDate()).toString(DEFAULT_DATE_FORMAT_PATTERN);
    }

    /**
     * 获取下月的第一天
     * if today - will 2020-09-21 14:14:14 -> 2020-10-01
     *
     * @return {@link Date}
     */
    public static Date getNextMonthFirstDayToDate() {
        return LocalDateTime.now().plusMonths(1).dayOfMonth().withMinimumValue().toDate();
    }

    /**
     * 获取下月的最后一天
     * if today - will 2020-09-21 14:14:14 -> 2020-10-31
     *
     * @return {@link Date}
     */
    public static Date getNextMonthLastDayToDate() {
        return LocalDateTime.now().plusMonths(1).dayOfMonth().withMaximumValue().toDate();
    }

    /**
     * 获取下月的第一天
     * if today - will 2020-09-21 14:14:14 -> 2020-10-01
     *
     * @return {@link String}
     */
    public static String getNextMonthFirstDayToString() {
        return new DateTime(getNextMonthFirstDayToDate()).toString(DEFAULT_DATE_FORMAT_PATTERN);
    }

    /**
     * 获取下月的最后一天
     * if today - will 2020-09-21 14:14:14 -> 2020-10-31
     *
     * @return {@link String}
     */
    public static String getNextMonthLastDayToString() {
        return new DateTime(getNextMonthLastDayToDate()).toString(DEFAULT_DATE_FORMAT_PATTERN);
    }

    /**
     * 解析时间
     *
     * @param date    指定日期
     * @param timeStr 时间  in format "hh:mm:ss"
     * @return {@link Date}
     */
    public static Date parseTime(Date date, String timeStr) {
        String dateFormat = getDateFormat(date, DEFAULT_DATE_FORMAT_PATTERN);
        return parseSpecificDateTime(dateFormat + " " + timeStr);
    }

    /**
     * 范围内每个月的第一天集合
     * 6.1 < 7.1 8.1 9.1 < 10.1 ==> 6789月份来算环比
     * <p>
     * monthFirstDayInRange("2020-12-01", "2020-10-01") -> [2020-12-01]
     * monthFirstDayInRange("2020-07-01", "2020-10-01") -> [2020-07-01, 2020-08-01, 2020-09-01]
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return [startDate, endDate) 左闭右开
     */
    public static List<String> monthFirstDayInRange(String startDate, String endDate) {
        List<String> stringList = new ArrayList<>();
        DateTime startDateTime = DateTimeFormat.forPattern(DEFAULT_DATE_FORMAT_PATTERN).parseDateTime(startDate).dayOfMonth().withMinimumValue();
        DateTime endDateTime = DateTimeFormat.forPattern(DEFAULT_DATE_FORMAT_PATTERN).parseDateTime(endDate).dayOfMonth().withMinimumValue();
        stringList.add(startDateTime.toString(DEFAULT_DATE_FORMAT_PATTERN));
        if (endDateTime.compareTo(startDateTime) < 0) {
            return stringList;
        }
        while (startDateTime.plusMonths(1).compareTo(endDateTime) < 0) {
            startDateTime = startDateTime.plusMonths(1);
            stringList.add(startDateTime.toString(DEFAULT_DATE_FORMAT_PATTERN));
        }
        return stringList;
    }

    /**
     * 去年的今天
     * lastYearTodayMonth("2020-07-01") -> 2019-07-01
     * lastYearTodayMonth("2020-10-30") -> 2019-10-01
     *
     * @param todayMonth 今天的月份
     * @return 去年的今天的月份
     */
    public static String lastYearTodayMonth(String todayMonth) {
        return DateTimeFormat.forPattern(DateUtil.DEFAULT_DATE_FORMAT_PATTERN).parseDateTime(todayMonth)
                .minusYears(1).dayOfMonth().withMinimumValue().toString(DateUtil.DEFAULT_DATE_FORMAT_PATTERN);
    }


    /**
     * 是合法的时间
     *
     * @param pattern 模式
     * @param time    时间
     * @return boolean
     */
    public static boolean isLegalTime(String pattern, String time) {
        try {
            DateTimeFormat.forPattern(pattern).parseDateTime(time);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 是合法的时间
     *
     * @param time 时间
     * @return boolean
     */
    public static boolean isLegalTime(String time) {
        return isLegalTime(DEFAULT_TIME_FORMAT_PATTERN, time);
    }

    public static void main(String[] args) {
        System.out.println(getMonthZh("2020-10-30"));
        Date date = parseTime(new Date(), "09:40:30");
        System.out.println(parseDefaultDate("2020-12-34"));
        System.out.println(date);
    }


}