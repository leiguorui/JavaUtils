package com.renrennet.utils.io.file;

import java.io.File;

/**
 * User: Green lei
 * Date: 15-4-22
 * Time: 上午10:00
 */
public class FilePath {
    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));

        System.out.println(FilePath.class.getClassLoader().getResource(""));

        System.out.println(ClassLoader.getSystemResource(""));
        System.out.println(FilePath.class.getResource(""));
        System.out.println(FilePath.class.getResource("/"));
        //Class文件所在路径
        System.out.println(new File("/").getAbsolutePath());
        System.out.println(System.getProperty("user.dir"));
    }
}
