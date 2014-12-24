package com.renrennet.utils;

import com.renrennet.utils.datetime.DateUtils;
import org.junit.Test;

/**
 * Created by leiguorui on 10/27/14.
 */
public class DateUtilsTest {
    @Test
    public void test(){
        System.out.println(DateUtils.getLastTime(18720000));
        System.out.println(DateUtils.addDaysToCurrent(10));
    }
}
