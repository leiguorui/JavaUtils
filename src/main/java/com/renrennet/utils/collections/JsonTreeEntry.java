package com.renrennet.utils.collections;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成json的树形结构
 * User: Green lei
 * Date: 15-4-28
 * Time: 下午4:34
 */
public class JsonTreeEntry {
    private String name;

    public JsonTreeEntry(String name) {
        this.name = name;
    }

    private List<JsonTreeEntry> children;

    public void add(JsonTreeEntry node){
        if (children == null)
            children = new ArrayList<JsonTreeEntry>();
        children.add(node);
    }

    public static void main(String[] args) {

        JsonTreeEntry workNode = new JsonTreeEntry("Work");
        workNode.add(new JsonTreeEntry("Effort"));
        workNode.add(new JsonTreeEntry("Trust"));

        JsonTreeEntry salaryNode = new JsonTreeEntry("Salary");


        JsonTreeEntry cultureNode = new JsonTreeEntry("Culture");
        cultureNode.add(salaryNode);
        cultureNode.add(workNode);

        Gson g = new Gson();

        System.out.println(g.toJson(cultureNode));
    }
}
