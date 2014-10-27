package com.renrennet.utils;

import com.sun.deploy.net.HttpRequest;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Enumeration;

/**
 * Created by leiguorui on 10/11/14.
 *
 * 这个类是将用户登陆信息保存到memcached中
 * 这个类中的String userId 可以替换成实际项目中的User user
 */
public class MemcachedUtil {
    protected static MemcachedClient client;

    static {
        try {
            client = new MemcachedClient(new InetSocketAddress(
                    "localhost", 11211));
        } catch (IOException e) {
            System.out.println("MemcachedClient建立异常");
            e.printStackTrace();
        }
    }

    /**
     * 保存用户id到memcached
     * @param session
     * @param userId
     * @return
     */
    public static OperationFuture<Boolean> setSessionUser(HttpSession session, String userId){
        //设置为最新的user
        String key = getSessionId(session);
        if(key != null){
            client.delete(key);
        }
        return client.set(getSessionId(session), 3600, userId);
    }

    /**
     * 获取用户id
     * @param session
     * @return
     */
    public static String getSessionUserId(HttpSession session) {
        return  client.get(getSessionId(session)).toString();
    }

    /**
     * 用户注销
     * @param session
     * @return
     */
    public static OperationFuture<Boolean> delSessionUser(HttpSession session) {
        return  client.delete(getSessionId(session));
    }

    public static String getSessionId(HttpSession session) {
        String sessionId = session.getId();
        if(sessionId != null){
            sessionId = sessionId.split("-")[0];
        }

        return  sessionId;
    }
}
