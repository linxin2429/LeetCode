package com.xldeng.solution1_20;

/**
 * Created on 2020/9/1.
 *
 * @author xldeng
 */
public class Solution7 {
    public int reverse(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x /= 10;
        }
        return (int) n == n ? (int) n : 0;
    }
}
