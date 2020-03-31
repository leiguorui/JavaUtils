package com.renrennet.utils.serialize;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.renrennet.utils.io.gson.BooleanAsIntAdapter;
import com.renrennet.utils.io.serialize.SerializeUtil;
import com.renrennet.utils.io.serialize.protostuff.SerializeUtilsProtostuff;
import org.junit.Test;

/**
 * @Auther: leiguorui
 * @Date: 2020/3/31 14:48
 * @Description:
 */

public class SerializeUtilsProtostuffTest {
    @Test
    public void booleanAsInt(){
        System.out.println(SerializeUtilsProtostuff.serialize("asdf").length);
        System.out.println(SerializeUtil.serialize("asdf").length);
    }
}
