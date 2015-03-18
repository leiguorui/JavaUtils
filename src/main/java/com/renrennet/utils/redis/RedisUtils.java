package com.renrennet.utils.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Iterator;
import java.util.Set;

/**
 * redis 的基本操作
 * User: Administrator
 * Date: 15-3-16
 * Time: 下午5:55
 */
public class RedisUtils {
    private static RedisUtils uniqueInstance = null;

    //TODO 此处应该优化连接池
    JedisPool pool = new JedisPool(new JedisPoolConfig(), "10.0.0.28");
    Jedis jedis = pool.getResource();

    private RedisUtils() {
        // Exists only to defeat instantiation.
    }

    public static RedisUtils getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new RedisUtils();
        }
        return uniqueInstance;
    }

    public Jedis getJedis(){
        return jedis;
    }

    public void getKeys(){
        System.out.println("Connection to server sucessfully");
        //store data in redis list
        // Get the stored data and print it
        Set<String> set = jedis.keys("*");
        for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
            String f = it.next();
            System.out.println("List of stored keys:: "+f);
        }
    }

    public static void main(String[] args){
//        Jedis jedis = RedisUtils.getInstance().getJedis();
//
////        jedis.set("car_match_hello", "bar");
//        String value = jedis.get("car_match_hello");
//        System.out.println(value);

        RedisUtils.getInstance().getKeys();
    }
}
