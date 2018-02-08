package io.stack.pj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A util class to handle many Date related conversion and formatting.
 *
 * @author Prajin Maharjan
 * @since 1.0
 */
public final class DateFormatUtil {

    private static final long MS_PER_DAY = 1000 * 60 * 60 * 24;
    public static final String SHORT_DATE = "dd-MM-yyyy";
    public static final String LONG_DATE = "dd-MM-yyyy hh:mm:ss";
    public static final SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat(SHORT_DATE);
    public static final SimpleDateFormat LONG_DATE_FORMAT = new SimpleDateFormat(LONG_DATE);

    public static String formatDateShort(Date date) {
        if (date == null) {
            return "N/A";
        }
        return SHORT_DATE_FORMAT.format(date);
    }

    public static String formatDateLong(Date date) {
        if (date == null) {
            return "N/A";
        }
        return LONG_DATE_FORMAT.format(date);
    }

    public static String formatDateShort(String date) {
        if (date == null) {
            return "N/A";
        }
        return SHORT_DATE_FORMAT.format(date);
    }

    public static Date formatDateLong(String date) {
        if (date == null || date.length() == 0) {
            return null;
        }
        try {
            return LONG_DATE_FORMAT.parse(date);
        } catch (ParseException parse) {
            return null;
        }
    }

    public static Calendar getCalendar() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    public static Date addMinuteOnDate(Date date, int minute) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static Date subtractMinuteOnDate(Date date, int minute) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -minute);
        return calendar.getTime();
    }

    /**
     * convert {@link String} to {@link Date}
     *
     * @param stringDate
     * @return
     */
    public static Date convertStringToDate(String stringDate) {
        try {
            return SHORT_DATE_FORMAT.parse(stringDate);
        } catch (ParseException parse) {
            System.out.println(parse.getMessage());
            return null;
        }
    }

    /**
     * Calculates number of days between two provided dates.
     *
     * @param from or start date
     * @param to   or end date
     * @return no. of days in between
     */
    public static int getDaysInBetween(Date from, Date to) {
        return (int) Math.ceil((double) (to.getTime() - from.getTime()) / MS_PER_DAY);
    }

    private DateFormatUtil() {
    }
}
