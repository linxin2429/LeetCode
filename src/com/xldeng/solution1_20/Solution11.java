package com.xldeng.solution1_20;

/**
 * Created on 2020/9/2.
 *
 * @author xldeng
 */
public class Solution11 {
    public int maxArea(int[] height) {
        int len = height.length;
        int m = 0, n = len - 1;
        int maxArea = 0;
        int currentArea = 0;
        while (n > m){
            currentArea = (n - m) * Math.min(height[m],height[n]);
            if (currentArea > maxArea ){
                maxArea = currentArea;
            }
            if(height[m] > height[n]){
                n--;
            } else {
                m++;
            }
        }
        return maxArea;
    }
}
