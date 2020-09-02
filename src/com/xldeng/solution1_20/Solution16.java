package com.xldeng.solution1_20;

import java.util.Arrays;

/**
 * Created on 2020/9/2.
 *
 * @author xldeng
 */
public class Solution16 {
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        int closestNum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = len - 1;
            while (j < k) {
                int currentNum = nums[i] + nums[j] + nums[k];
                if (currentNum == target) {
                    return currentNum;
                }
                if (Math.abs(currentNum - target) < Math.abs(closestNum - target)) {
                    closestNum = currentNum;
                }
                if (currentNum > target) {
                    --k;
                    // 排除相同的c元素，加速运算
                    while (k > j && nums[k] == nums[k + 1]){
                        --k;
                    }
                } else {
                    ++j;
                    while (k > j && nums[j] == nums[j - 1]){
                        ++j;
                    }
                }
            }
        }
        return closestNum;
    }
}
