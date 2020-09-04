package com.xldeng.binarySearch;

/**
 * Created on 2020/9/4.
 *
 * @author xldeng
 */
public class Solution704 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return -1;
    }
}
