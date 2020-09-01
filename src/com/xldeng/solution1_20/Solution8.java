package com.xldeng.solution1_20;

/**
 * Created on 2020/9/1.
 *
 * @author xldeng
 */
public class Solution8 {
    public int myAtoi(String str) {
        // 去空格
        str = str.trim();
        int len = str.length();
        if (len == 0) {
            return 0;
        }
        char firstChar = str.charAt(0);
        // 首字符不是正负号或数字
        if (!Character.isDigit(firstChar)
                && '+' != firstChar
                && '-' != firstChar) {
            return 0;
        }
        boolean flag = firstChar == '-';
        long ans = 0L;
        int i = Character.isDigit(firstChar) ? 0 : 1;
        char temp;
        while (i < len && Character.isDigit(temp = str.charAt(i))) {
            ans = ans * 10 + (temp - '0');
            if (flag && ans > 1L + Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE + 1L;
                break;
            }
            if (!flag && ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
                break;
            }
            i++;
        }
        return flag ? (int) -ans : (int) ans;
    }
}
