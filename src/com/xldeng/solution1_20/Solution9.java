package com.xldeng.solution1_20;

/**
 * Created on 2020/9/1.
 *
 * @author xldeng
 */
public class Solution9 {
    public boolean isPalindrome(int x) {
        if (x < 0){
            return false;
        }
        // 先反转x
        int y = x;
        long n = 0L;
        while (x != 0){
            n = n * 10 + x % 10;
            x /= 10;
        }
        if (n > Integer.MAX_VALUE){
            return false;
        }
        return (int) n == y;
    }
}
