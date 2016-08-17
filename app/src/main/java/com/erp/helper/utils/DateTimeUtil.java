package com.erp.helper.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class DateTimeUtil {

    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String DATE_DISPLAY_FORMAT = "dd-MM-yyyy";

    public static String DATETIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static String DATETIME_DISPLAY_FORMAT = "dd-MM-yyyy hh:mm:ss";

    public static String TIME_FORMAT = "hh:mm:ss";
    public static String TIME_DISPLAY_FORMAT = "hh:mm:ss";

    /* ------------------------ */
    /* -- From ... To String -- */
    /* ------------------------ */

    public static String ToDateString(Calendar calendar) {
        return ToStringFormat(calendar, DATE_FORMAT);
    }

    public static String ToDateString(String textDateDisplay) {
        return ToDateString(FromDateDisplayString(textDateDisplay));
    }

    public static String ToDateDisplayString(Calendar calendar) {
        return ToStringFormat(calendar, DATE_DISPLAY_FORMAT);
    }

    public static String ToDateDisplayString(String textDate) {
        return ToDateDisplayString(FromDateString(textDate));
    }

    public static String ToStringFormat(Calendar calendar, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        if (calendar != null)
            return simpleDateFormat.format(calendar.getTime());
        else
            return null;
    }

    /* -------------------------- */
    /* -- From ... To Calendar -- */
    /* -------------------------- */

    public static Calendar FromDateString(String text) {
        return FromString(text, DATE_FORMAT);
    }

    public static Calendar FromDateDisplayString(String text) {
        return FromString(text, DATE_DISPLAY_FORMAT);
    }

    public static Calendar FromString(String text, String format) {
        Calendar calendar;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
            calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(text));
        }
        catch (Exception ex) {
            calendar = null;
        }

        return calendar;
    }

    public static Calendar FromDate(int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        return calendar;
    }
}
