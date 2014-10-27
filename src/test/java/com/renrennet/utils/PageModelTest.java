package com.renrennet.utils;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by leiguorui on 10/27/14.
 */
public class PageModelTest {
    @Test
    public void createPage(){

        // 创建一个分页
        PageModel pageModel=new PageModel();
        pageModel.setPageNo(1);
        pageModel.setPageSize(5);
        pageModel.setTotalRecords(10);
        pageModel.setList(new ArrayList<String>());

    }
}
