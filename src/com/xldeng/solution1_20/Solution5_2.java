package com.xldeng.solution1_20;

public class Solution5_2 {
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        String ans = s.substring(0, 1);
        for (int i = 0; i < length - 1; i++) {
            String oddString = getMaxString(s, i, i);
            String evenString = getMaxString(s, i, i + 1);
            String temp = oddString.length() > evenString.length() ? oddString : evenString;
            if (temp.length() > ans.length()) {
                ans = temp;
            }
        }
        return ans;
    }

    private String getMaxString(String s, int left, int right) {
        int length = s.length();
        while (left >= 0 && right < length) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return s.substring(left + 1, right);
    }
}
