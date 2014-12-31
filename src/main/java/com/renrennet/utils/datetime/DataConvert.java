package com.renrennet.utils.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by leiguorui on 12/31/14.
 *
 * 时间转换
 */
public class DataConvert {

    /**
     * 当前时间往后几天的日期，days为负值表示往前数几天
     * @param days
     * @return
     */
    public static Date addDaysToCurrent(int days){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 根据持续毫秒值，获取时分秒
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

    /**
     * String类型的时间，转为Date
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
