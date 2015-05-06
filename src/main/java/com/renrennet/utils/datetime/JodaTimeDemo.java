package com.renrennet.utils.datetime;

import org.joda.time.YearMonth;

/**
 * JodaTime时间转换工具的demo
 *
 * JodaTime相比jdk中的Date和Calendar更简单
 *
 * User: Green lei
 * Date: 15-5-6
 * Time: 上午10:05
 */
public class JodaTimeDemo {
    /**
     * 获取某个月的前toSize个月
     * @param fromMonth 某个月
     * @param toSize    负数为向前数，正数为向后数
     * @return
     */
    public static YearMonth getPreviousMonth (YearMonth fromMonth, int toSize){
        return fromMonth.minusMonths(toSize) ;
    }

    public static void main(String[] args){
        YearMonth monthForSample = new YearMonth(2015,1);
        for (int monthNo = -2; monthNo < 3; monthNo++){
            YearMonth sampleMoth = JodaTimeDemo.getPreviousMonth(monthForSample,monthNo);

            System.out.println(sampleMoth.toString());
        }
    }
}
