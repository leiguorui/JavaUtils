package com.renrennet.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by leiguorui on 9/26/14.
 *
 * 时间转换工具类
 */
public class DateUtils {
    /**
     * 获取时间yyyy/MM/dd HH:mm:ss
     * @return
     */
    public static String getNowDateTime(){
        Date nowDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(nowDate);
    }

    /**
     * 根据周次获取周一和周日的日期
     * @param weekNo
     * @param yearNo
     * @return
     */
    public static String[] getDatesByWeek(int weekNo, int yearNo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearNo);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String from = sdf.format(cal.getTime());

        cal.set(Calendar.WEEK_OF_YEAR, weekNo+1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String to = sdf.format(cal.getTime());

        return new String[]{from, to};
    }

    /**
     * 返回当前日期 yyyy-MM-dd
     * @return
     */
    public static String getNowDate() {
        Date nowDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(nowDate);
    }

    /**
     * 获取当前周
     * @return int
     */
    public static int getWeekNumber(){
        return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前月
     * @return
     */
    public static String getMonthNumber(){
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        String monthStr = Integer.toString(month);
        if(month<10){
            monthStr = "0"+month;
        }
        return monthStr;
    }

    /**
     * 获取当前年
     * @return
     */
    public static int getYearNumber(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取持续时间
     * @param millis
     * @return
     */
    public static String getLastTime(long millis){
        long second = (millis / 1000) % 60;
        long minute = (millis / (1000 * 60)) % 60;
        long hour = (millis / (1000 * 60 * 60)) % 24;

        String time = String.format("%02d:%02d:%02d", hour, minute, second);
        return time;
    }
}
