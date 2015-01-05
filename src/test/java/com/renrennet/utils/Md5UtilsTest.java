package com.renrennet.utils;

import com.renrennet.utils.security.Md5Utils;
import org.junit.Test;

/**
 * Created by leiguorui on 10/27/14.
 */
public class Md5UtilsTest {
    @Test
    public void createMd5(){
        String origin = "123456";
        String encrypt = Md5Utils.digestedHex(origin);
    }
}
