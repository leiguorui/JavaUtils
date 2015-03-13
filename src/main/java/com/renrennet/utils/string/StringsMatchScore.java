package com.renrennet.utils.string;

/**
 * 计算字符串的相似度
 *
 * 教程可以参考：
 * 1.http://www.cnblogs.com/grenet/archive/2010/06/04/1751147.html
 * 2.http://rosettacode.org/wiki/Levenshtein_distance
 * 3.http://rosettacode.org/wiki/Longest_common_subsequence#Dynamic_Programming_2
 *
 * User: Administrator
 * Date: 15-3-13
 * Time: 下午3:37
 */
public class StringsMatchScore {
    /**
     * LD(A,B)算法的实现，表示字符串之间的编辑距离
     * @param s0
     * @param s1
     * @return
     */
    public static int levenshteinDistance (String s0, String s1) {
        int len0 = s0.length() + 1;
        int len1 = s1.length() + 1;

        // the array of distances
        int[] cost = new int[len0];
        int[] newcost = new int[len0];

        // initial cost of skipping prefix in String s0
        for (int i = 0; i < len0; i++) cost[i] = i;

        // dynamically computing the array of distances

        // transformation cost for each letter in s1
        for (int j = 1; j < len1; j++) {
            // initial cost of skipping prefix in String s1
            newcost[0] = j;

            // transformation cost for each letter in s0
            for(int i = 1; i < len0; i++) {
                // matching current letters in both strings
                int match = (s0.charAt(i - 1) == s1.charAt(j - 1)) ? 0 : 1;

                // computing cost for each transformation
                int cost_replace = cost[i - 1] + match;
                int cost_insert  = cost[i] + 1;
                int cost_delete  = newcost[i - 1] + 1;

                // keep minimum cost
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            // swap cost/newcost arrays
            int[] swap = cost; cost = newcost; newcost = swap;
        }

        // the distance is the cost for transforming all letters in both strings
        return cost[len0 - 1];
    }

    /**
     * LCS(A,B)的算法实现，表示两字符串之间最长公共子串的长度
     * @param a
     * @param b
     * @return
     */
    public static int longestCommonSubsequence(String a, String b) {
        int[][] lengths = new int[a.length()+1][b.length()+1];

        // row 0 and column 0 are initialized to 0 already

        for (int i = 0; i < a.length(); i++)
            for (int j = 0; j < b.length(); j++)
                if (a.charAt(i) == b.charAt(j))
                    lengths[i+1][j+1] = lengths[i][j] + 1;
                else
                    lengths[i+1][j+1] =
                            Math.max(lengths[i+1][j], lengths[i][j+1]);

        // read the substring out from the matrix
        StringBuffer sb = new StringBuffer();
        for (int x = a.length(), y = b.length();
             x != 0 && y != 0; ) {
            if (lengths[x][y] == lengths[x-1][y])
                x--;
            else if (lengths[x][y] == lengths[x][y-1])
                y--;
            else {
                assert a.charAt(x-1) == b.charAt(y-1);
                sb.append(a.charAt(x-1));
                x--;
                y--;
            }
        }

        return sb.reverse().length();
    }

    public static void main(String[] args){
        String s1 = "2002款 A6 2.4 CVT";
        String s2 = "A6 2.4 CVT技术领先型";
        String s3 = "2.4 CVT";

        double ld1_2 = levenshteinDistance(s1,s2);
        double ld2_3 = levenshteinDistance(s2,s3);
        double ld1_3 = levenshteinDistance(s1,s3);

        double lcs1_2 = longestCommonSubsequence(s1, s2);
        double lcs2_3 = longestCommonSubsequence(s2, s3);
        double lcs1_3 = longestCommonSubsequence(s1, s3);

        double score1_2 = lcs1_2/(lcs1_2+ld1_2);
        double score2_3 = lcs2_3/(lcs2_3+ld2_3);
        double score1_3 = lcs1_3/(lcs1_3+ld1_3);

        System.out.println("1-2的相似度最高");
        System.out.println("-----------------------");
        System.out.println("1-2的相似度： "+score1_2);
        System.out.println("2-3的相似度： "+score2_3);
        System.out.println("1-3的相似度： "+score1_3);
    }
}
