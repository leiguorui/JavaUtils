package com.renrennet.utils.string;

import java.util.*;

/**
 * 一个简单的分词器，把一段话根据词典断句
 *
 * 教程：http://www.programcreek.com/2014/03/leetcode-word-break-ii-java/
 *
 * User: Administrator
 * Date: 15-3-31
 * Time: 下午3:16
 */
public class WordSegmentation {
    public static List<String> wordBreak(String s, Set<String> dict) {
        //create an array of ArrayList<String>
        List<String> dp[] = new ArrayList[s.length()+1];
        dp[0] = new ArrayList<String>();

        for(int i=0; i<s.length(); i++){
            if( dp[i] == null )
                continue;

            for(String word:dict){
                int len = word.length();
                int end = i+len;
                if(end > s.length())
                    continue;

                if(s.substring(i,end).equals(word)){
                    if(dp[end] == null){
                        dp[end] = new ArrayList<String>();
                    }
                    dp[end].add(word);
                }
            }
        }

        List<String> result = new LinkedList<String>();
        if(dp[s.length()] == null)
            return result;

        ArrayList<String> temp = new ArrayList<String>();
        dfs(dp, s.length(), result, temp);

        return result;
    }

    public static void dfs(List<String> dp[],int end,List<String> result, ArrayList<String> tmp){
        if(end <= 0){
            String path = tmp.get(tmp.size()-1);
            for(int i=tmp.size()-2; i>=0; i--){
                path += " " + tmp.get(i) ;
            }

            result.add(path);
            return;
        }

        for(String str : dp[end]){
            tmp.add(str);
            dfs(dp, end-str.length(), result, tmp);
            tmp.remove(tmp.size()-1);
        }
    }

    public static void main(String[] args){
        String s = "中国是个好地方";
        String[] dict = {"中国", "是", "个", "好", "地方", "好地方"};
        Set<String> dictSet = new HashSet<String>();
        for(String str:dict){
            dictSet.add(str);
        }

        List<String> result = wordBreak(s, dictSet);
        for(String str:result){
            System.out.println(str);
        }
    }
}
