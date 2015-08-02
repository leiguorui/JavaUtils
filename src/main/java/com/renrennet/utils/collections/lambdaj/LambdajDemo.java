package com.renrennet.utils.collections.lambdaj;

import ch.lambdaj.Lambda;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * lambdaj demo
 * Created by Green Lei on 2015/8/2 17:59.
 */
public class LambdajDemo {
    //测试数据
    List<String> stringList;
    List<Person> personList;

    public LambdajDemo() {
        stringList = Arrays.asList("hari1", "hari2", "hari3", "hari4");
        personList = Arrays.asList(
                new Person(13,"Steve",22,"London, UK"),
                new Person(25,"Greg",28,"New York, USA"),
                new Person(5,"Emily",22,"Bali, Indonesia"),
                new Person(9,"Malih",14,"Jakarta, Indonesia"));
    }

    /**
     * 使用join，把list拼接成字符串,用","分割
     */
    @Test
    public void join(){
        String result = Lambda.join(stringList, ",");

        System.out.println(result);
    }

    /**
     * List转换为Map
     */
    @Test
    public void index(){
        Map<Integer,Person> personMap = Lambda.index(personList, Lambda.on(Person.class).getId());

        System.out.println("Person name with ID 5: " + personMap.get(5).getName());
    }
}
