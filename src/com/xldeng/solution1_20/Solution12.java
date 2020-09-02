package com.xldeng.solution1_20;

/**
 * Created on 2020/9/2.
 *
 * @author xldeng
 */
public class Solution12 {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length && num != 0; i++) {
            while (num > values[i]) {
                num -= values[i];
                roman.append(romans[i]);
            }
        }
        return roman.toString();
    }
}
