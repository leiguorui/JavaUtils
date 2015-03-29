package com.renrennet.utils.string;

import java.io.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式的测试工具
 * 用法：
 * 1.运行main
 * 2.输入正则表达式
 * 3.输入要匹配的String
 * 4.输出：正则匹配到的串在String中的位置
 *
 * 教程：http://www.java3z.com/cwbwebhome/article/article8/Regex/Java.Regex.Tutorial.html
 *
 * User: Administrator
 * Date: 15-3-29
 * Time: 下午3:16
 */
public class RegexTestUtil {

    public static void main(String[] args) {
        Console console = System.console();
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        while (true) {
            Pattern pattern = Pattern.compile(console.readLine("%nEnter your regex: "));
            Matcher matcher = pattern.matcher(console.readLine("Enter input string to search: "));
            boolean found = false;
            while (matcher.find()) {
                console.format("I found the text \"%s\" starting at index %d " +
                        "and ending at index %d.%n",
                        matcher.group(), matcher.start(), matcher.end());
                found = true;
            }
            if (!found) {
                console.format("No match found.%n");
            }
        }
    }
}
