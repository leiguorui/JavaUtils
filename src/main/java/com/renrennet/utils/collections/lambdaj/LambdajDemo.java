package com.renrennet.utils.collections.lambdaj;

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.StringContains;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    /**
     * 抽取一个字段到新的List中
     */
    @Test
    public void extract(){
        List<String> personNames = Lambda.extract(personList, Lambda.on(Person.class).getName());

        System.out.println("personNames: "+personNames);
    }

    /**
     * 选择City为Indonesia的Persion
     */
    @Test
    public void selectByHaving(){
        List<Person> filteredPerson = Lambda.select(
                personList,
                Lambda.having(Lambda.on(Person.class).getCity(), StringContains.containsString("Indonesia")));

        for (Person person : filteredPerson)
            System.out.println(person.getDetails());
    }

    /**
     * 选择Age为22的Persion到List中，然后从List中抽取Name
     */
    @Test
    public void extractBySelect(){
        List<String> filteredPersonNames = Lambda.extract(
                Lambda.select(personList,
                        Lambda.having(Lambda.on(Person.class).getAge(),
                                IsEqual.equalTo(22))),
                Lambda.on(Person.class).getName());

        System.out.println("filteredPersonNames: "+filteredPersonNames);
    }

    /**
     * 分组
     */
    @Test
    public void group(){
        Group<Person> groupAgeOfPerson = Lambda.group(personList, Lambda.by(Lambda.on(Person.class).getAge()));
        Set<String> groupAgeKeys  = groupAgeOfPerson.keySet();

        System.out.println("groupAgeKeys: "+groupAgeKeys);
        System.out.println("groupOfAge: "+groupAgeOfPerson.find(22));

        for (String ageKey : groupAgeKeys)
            for (Person person : groupAgeOfPerson.find(ageKey))
                System.out.println(person.getDetails());
    }
}
