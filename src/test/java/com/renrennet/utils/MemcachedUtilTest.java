package com.renrennet.utils;

import com.renrennet.utils.memcached.MemcachedUtil;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.PrintWriter;

/**
 * Created by leiguorui on 10/27/14.
 */
public class MemcachedUtilTest {
    @Test
    public void test(HttpSession session, String userId){
        //添加，修改用户
        MemcachedUtil.setSessionUser(session, userId);

        //获取用户
        MemcachedUtil.getSessionUserId(session);

        //删除用户
        MemcachedUtil.delSessionUser(session);
    }
}
