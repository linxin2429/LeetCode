package com.xldeng.solution1_20;

/**
 * Created on 2020/9/2.
 *
 * @author xldeng
 */
public class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (len == 0 ){
            return "";
        }
        // 以strs[0]为基准，遍历其他字符串，比较对应位置的字符是否相同，不同则退出循环，找到公共前缀
        String ans = strs[0];
        for (int i = 1; i < len; i++) {
            int j = 0;
            for (; j < ans.length() && j < strs[i].length(); j++) {
                if (ans.charAt(j) != strs[i].charAt(j)){
                    break;
                }
            }
            ans = ans.substring(0,j);
            if ("".equals(ans)){
                return "";
            }
        }
        return ans;
    }
}
