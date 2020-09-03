package com.xldeng.solution1_20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2020/9/2.
 *
 * @author xldeng
 */
public class Solution18 {
    // 优化后39ms -> 6ms
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            // 优化：如果nums[i]与最大的三个数相加依然小于target，那么这个数可以跳过
            int maxi1 = nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3];
            if (maxi1 < target){
                continue;
            }
            // 优化：如果nums[i]与最小的三个数相加依然大于target，循环可以终止
            int maxi2 = nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (maxi2 > target){
                continue;
            }

            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                // 优化：如果nums[i],nums[j]与最大的两个数相加依然小于target，那么这个数可以跳过
                int maxj1 = nums[i] + nums[len - 1] + nums[len - 2] + nums[j];
                if (maxj1 < target){
                    continue;
                }
                // 优化：如果nums[i]，nums[]与最小的三个数相加依然大于target，循环可以终止
                int maxj2 = nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
                if (maxi2 > target){
                    continue;
                }

                int k = j + 1;
                int l = len - 1;
                while (k < l){
                    int sum = nums[i] + nums[j]+ nums[k] + nums[l];
                    if (sum == target){
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[l]);
                        lists.add(list);
                    }
                    if (sum > target){
                        --l;
                        while (l > k && nums[l] == nums[l+1]){
                            --l;
                        }
                    } else {
                        ++k;
                        while (l > k && nums[k] == nums[k-1]){
                            ++k;
                        }
                    }
                }
            }
            
        }
        return lists;
    }
}
