package com.xldeng.solution1_20;

public class Solution5 {
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        // dp记录回文开始和结束索引
        boolean[][] dp = new boolean[length][length];
        char[] sChars = s.toCharArray();
        // 回文开始索引
        int begin = 0;
        // 回文最大长度
        int maxLength = 1;
        // 初始化每个字符都是回文
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < length; j++) {
            for (int i = 0; i < j; i++) {
                // 子串首尾不同
                if (sChars[i] != sChars[j]) {
                    dp[i][j] = false;
                }
                // 子串首尾相同
                else {
                    // 子串长度小于4 可直接判断为true
                    if (j - i < 3) {
                        dp[i][j] = true;
                    }
                    // 子串首尾相同，并且子串长度大于4，该情况与去掉子串首尾的情形一致
                    else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > maxLength) {
                    begin = i;
                    maxLength = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + maxLength);
    }

}
