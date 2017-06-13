package com.wpmac.androidnougatframework.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: wpmac  Date: 2017/6/2 Time: 下午7:49
 * @email: mzeht8303@gamil.com
 */
public class TimeUtils {

    /**
     *
     * @param time
     * @return
     */
    public static String TimestampToDate(Integer time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long temp = (long)time*1000;
        String date = sdf.format(new Date(temp));
        return date;

    }

    public static Long getDayBeginTime(int day){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis()/1000;
    }

    public static Long getDayEndTime(int day){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTimeInMillis()/1000;
    }

    public static Long getMonthBeginTime(int month){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis()/1000;
    }


    public static Long getMonthEndTime(int month){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTimeInMillis()/1000;
    }

    public static String getMonthTimeDesc(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String date = sdf.format(new Date(calendar.getTimeInMillis()));
        return date;

    }


    public static String getDayTimeDesc(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date(calendar.getTimeInMillis()));
        return date;

    }

    public static String getHourAndMin(Long time){
        String formats="HH:mm";
        return TimeStamp2Date(time.toString(), formats);


    }

    public static String getYmDhMs(Long time){
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:SS");
        String date = sdf.format(new Date(time*1000));
        return date;

    }

    public static String getmD(Long time){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        String date = sdf.format(new Date(time*1000));
        return date;

    }



    public static String TimeStamp2Date(String timestampString, String formats){
        Long timestamp = Long.parseLong(timestampString)*1000;
        String date = new SimpleDateFormat(formats).format(new Date(timestamp));
        return date;
    }
}
