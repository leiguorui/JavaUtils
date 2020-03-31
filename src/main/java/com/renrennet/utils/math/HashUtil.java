package com.renrennet.utils.math;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * @Auther: leiguorui
 * @Date: 2020/3/25 12:23
 * @Description:
 */

public class HashUtil {

    /**
     * 在槽位为2的hash环中， 位置是否是0
     * @param input
     * @return
     */
    public static boolean isZeroIn2buckets(String input){
        return 0 == Hashing.consistentHash(Hashing.md5().hashString(input, Charsets.UTF_8),2);
    }

    /**
     * 获取桶的位置
     * @param input key
     * @param buckets 总共的集群个数
     * @return key所在的桶位置， 从0开始
     */
    public static int getBucketNo(String input, int buckets){
        return Hashing.consistentHash(Hashing.md5().hashString(input, Charsets.UTF_8),buckets);
    }
}
