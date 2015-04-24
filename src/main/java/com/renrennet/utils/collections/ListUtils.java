package com.renrennet.utils.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Green lei
 * Date: 15-4-24
 * Time: 下午2:55
 */
public class ListUtils {
    /**
     * 删除list中的重复项
     * @param list
     * @return
     */
    public static List<String> removeDuplicates(List<String> list){
        Object[] objects = list.toArray();
        for (Object object : objects) {
            if (list.indexOf(object) != list.lastIndexOf(object)) {
                list.remove(list.lastIndexOf(object));
            }
        }

        return list;
    }

    public static void main(String[] args){
        List<String> lst = new ArrayList<String>();
        lst.add("ABC");
        lst.add("ABC");
        lst.add("ABCD");
        lst.add("ABCD");
        lst.add("ABCE");
        System.out.println("Duplicates List "+lst);
        lst =  removeDuplicates(lst);
        System.out.println("Distinct List "+lst);
    }
}
