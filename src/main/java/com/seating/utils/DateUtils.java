package com.seating.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date parseDate(String date, String time) throws ParseException {
        String dateTimeStr = date + " " + time;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.parse(dateTimeStr);
    }
}

