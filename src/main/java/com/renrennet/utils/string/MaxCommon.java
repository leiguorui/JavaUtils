package com.renrennet.utils.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 求最长公共字符
 * User: Administrator
 * Date: 15-3-30
 * Time: 上午10:30
 */
public class MaxCommon {
    /**
     * 取两个String中相同的字符个数
     * @param str1
     * @param str2
     * @return
     */
    public static int getCommonCount(String str1, String str2) {
        String str11 = "";
        String str12 = "";
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        int count = 0;
        for (int i = 0; i < ch1.length; i++) {
            if (!str11.contains(ch1[i] + "")) {
                str11 += ch1[i];
            }
        }
        for (int i = 0; i < ch2.length; i++) {
            if (!str12.contains(ch2[i] + "")) {
                str12 += ch2[i];
            }
        }
        char[] ch11 = str11.toCharArray();
        char[] ch12 = str12.toCharArray();
        for (int i = 0; i < ch11.length; i++) {
            for (int j = 0; j < ch12.length; j++) {
                if (ch11[i] == ch12[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 求两个字符数组中，相同的字符串个数
     * @param strings1
     * @param strings2
     * @return
     */
    public static int getCommonCountStringArray(String[] strings1, String[] strings2){
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();

        int count = 0;
        for (int i = 0; i < strings1.length; i++) {
            if (!list1.contains(strings1[i] + "")) {
                list1.add(strings1[i]);
            }
        }
        for (int i = 0; i < strings2.length; i++) {
            if (!list2.contains(strings2[i] + "")) {
                list2.add(strings2[i]);
            }
        }
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                if (list1.get(i).equals(list2.get(j))) {
                    count++;
                }
            }
        }
        return count;
    }
}
