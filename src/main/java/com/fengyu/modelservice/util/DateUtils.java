package com.fengyu.modelservice.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具.
 */
public class DateUtils {
    /**
     * 东8区.
     */
    private static final int HOUR_LENGTH = 8;

    /**
     * 当前月份.
     * @return
     */
    public static String getCurrentMonth(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        return format.format(new Date());
    }
    /**
     * 当前时间.
     * @return
     */
    public static String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        return format.format(new Date());
    }

    public static Date abtainUTC() {
        Calendar calendar = Calendar.getInstance();
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int mil = calendar.get(Calendar.MILLISECOND);
        StringBuffer utcTimeBuffer = new StringBuffer();
        utcTimeBuffer.append(year).append("-").append(month).append("-").append(day).append(" ").append(hour)
                .append(":").append(minute).append(":").append(second).append(".").append(mil);
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
            Date date = format.parse(utcTimeBuffer.toString());
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getDate() {
        Date date = abtainUTC();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, HOUR_LENGTH);
        Date beijingDate = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(beijingDate);
    }



}
