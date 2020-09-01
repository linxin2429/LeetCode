package com.xldeng.solution1_20;

/**
 * Created on 2020/9/1.
 *
 * @author xldeng
 */
public class Solution10 {
    public boolean isMatch(String s, String p) {
        int sLength = s.length();
        int pLength = p.length();
        // dp 表示前i个s字符和前j个p字符是否匹配
        boolean[][] dp = new boolean[sLength + 1][pLength + 1];
        dp[0][0] = true;
        for (int i = 0; i <= sLength; i++) {
            for (int j = 1; j <= pLength; j++) {
                if (p.charAt(j - 1) == '*') {
                    // *前的字符和*可以去除
                    dp[i][j] = dp[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        // i 和 j - 1 匹配上，加上不去除*前的字符和*的情况 i - 1 和 i 相同时，dp[i-1][j]=dp[i][j]
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[sLength][pLength];
    }

    /**
     * @return boolean
     * @Description: // 判断第i个s字符和第j个p字符是否匹配，第一个为0开始
     * @Author: xldeng
     * @Date: 2020/9/1 21:42
     * @Param [s, p, i, j]
     **/
    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
