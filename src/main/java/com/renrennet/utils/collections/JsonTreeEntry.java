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

    private List<JsonTreeEntry> children;

    public JsonTreeEntry(String name) {
        this.name = name;
    }

    /**
     * 添加元素
     * @param node
     * @return 是否添加成功
     */
    public boolean add(JsonTreeEntry node){
        boolean addResult = false;
        if (children == null){
            children = new ArrayList<>();
            children.add(node);
            addResult = true;
        }else {
            boolean flag = true;
            // 遍历子元素，如何name相同，则不添加
            for (JsonTreeEntry child : children){
                if (child.getName() != null && child.getName().equals(node.getName())){
                    flag = false;
                }
            }
            if (flag){
                children.add(node);
                addResult = true;
            }
        }

        return addResult;
    }


    /**
     * 获得叶子节点（没有children的节点）的个数
     * @param children
     * @return
     */
    public int getLeafSize(List<JsonTreeEntry> children){
        int leafSize = 0;

        if (children != null && children.size() > 0){
            for (JsonTreeEntry child : children){
                leafSize = leafSize + getLeafSize(child.children);
            }
        } else {
            leafSize = 1;
        }

        return leafSize;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChildren(List<JsonTreeEntry> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public List<JsonTreeEntry> getChildren() {
        return children;
    }

    public static void main(String[] args) {

        JsonTreeEntry workNode = new JsonTreeEntry("Work");
        workNode.add(new JsonTreeEntry("Effort"));
        workNode.add(new JsonTreeEntry("Trust"));
        workNode.add(new JsonTreeEntry("Trust"));

        JsonTreeEntry salaryNode = new JsonTreeEntry("Salary");


        JsonTreeEntry cultureNode = new JsonTreeEntry("Culture");
        cultureNode.add(salaryNode);
        cultureNode.add(salaryNode);
        cultureNode.add(workNode);

        Gson g = new Gson();

        System.out.println(g.toJson(cultureNode));
        //获取叶子节点的个数
        System.out.println(cultureNode.getLeafSize(cultureNode.getChildren()));
    }
}
