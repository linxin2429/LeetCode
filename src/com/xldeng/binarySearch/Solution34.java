package com.xldeng.binarySearch;

/**
 * Created on 2020/9/4.
 *
 * @author xldeng
 */
public class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int[] range = {-1,-1};
        int left = findRange(nums, target, true);
        if ( left == nums.length || nums[left] != target ){
            return range;
        }
        range[0] = left;
        range[1] = findRange(nums,target,false) - 1;
        return range;
    }
/**
 * @Description: // 先用left = true 得到返回值，
 *                  若为左边界，则确定存在右边界，用left = false求得右边界=返回值-1
 *                  若不是左边界，则直接返回[-1,-1]
 * @Author: xldeng
 * @Date: 2020/9/4 15:43
 * @Param [nums, target, left]
 * @return left == true 返回 左边界，或未查询到target，需要判断；
 *         left == false 返回右边界 + 1 。
 **/
    private int findRange(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target || (nums[mid] == target && left)) {
                hi = mid;
            } else  {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
