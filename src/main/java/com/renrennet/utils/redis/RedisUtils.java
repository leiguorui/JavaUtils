package com.renrennet.utils.redis;

import com.renrennet.utils.io.SerializeUtil;
import com.renrennet.utils.io.file.PropertiesRead;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Iterator;
import java.util.Set;

/**
 * redis 的基本操作
 *
 * redis的读写操作，使用单例合适吗？
 *
 * User: Administrator
 * Date: 15-3-16
 * Time: 下午5:55
 */
public class RedisUtils {
    private static RedisUtils uniqueInstance = null;

    //TODO 此处应该优化连接池
    JedisPool pool = new JedisPool(new JedisPoolConfig(), PropertiesRead.getInstance().getValue("redis.host"));
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

    /**
     * 查询所有的key
     */
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

    /**
     * 根据Key获取值
     * @param key
     * @return
     */
    public Object getValue(String key){
        byte[] getResult = null;

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            getResult = jedis.get(key.getBytes());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        Object result =  SerializeUtil.unserialize(getResult);

        return result;
    }

    /**
     * 设置值
     * @param key
     * @param value
     */
    public void setValue(String key, Object value){
        System.out.println("-----添加一条记录------"+key.toString()+"------------");
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(key.getBytes(), SerializeUtil.serialize(value));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static void main(String[] args){
        RedisUtils.getInstance().getKeys();
    }
}
