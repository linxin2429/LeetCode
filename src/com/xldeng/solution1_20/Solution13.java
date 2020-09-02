package com.xldeng.solution1_20;

/**
 * Created on 2020/9/2.
 *
 * @author xldeng
 */
public class Solution13 {
    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
    public int romanToInt(String s) {
        int preNum = getValue(s.charAt(0));
        int len = s.length();
        int value = 0;
        int currNum;
        for (int i = 1; i < len; i++) {
            currNum = getValue(s.charAt(i));
            if (preNum >= currNum){
                value += preNum;
            } else {
                value -= preNum;
            }
            preNum = currNum;
        }
        return value + preNum;
    }
}
