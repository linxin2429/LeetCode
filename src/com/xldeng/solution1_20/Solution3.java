package com.xldeng.solution1_20;

import java.util.HashSet;

/**
 * @ClassName: Solution3
 * @Description: 无重复字符的最长子串
 * @Author: xldeng
 * @Date: 2020/8/17 12:37
 * @Version: 1.0
 **/
public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int right = 0, ans = 0;
        int n = s.length();
        HashSet<Character> set = new HashSet<>();
        for(int left = 0; left < n; left++){
            if (left != 0){
                set.remove(s.charAt(left - 1));
            }
            while (right< n && !set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                right++;
            }
            ans = Math.max(ans, right - left);
            right--;
        }
        return ans;
    }
}