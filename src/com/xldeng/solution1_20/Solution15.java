package com.xldeng.solution1_20;

import java.util.*;

/**
 * Created on 2020/9/2.
 *
 * @author xldeng
 */
public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        // 排序
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            // 每次a都不相同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int k = len - 1;
            // b+c目标值
            int target = -nums[i];
            for (int j = i + 1; j < len; j++) {
                // 每次b都不同
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // b+c>target 则左移c
                while (j < k && nums[j] + nums[k] > target) {
                    k--;
                }
                // 不允许j k 重合
                if (j == k) {
                    break;
                }
                if (nums[j] + nums[k] == target) {
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(nums[i]);
                    integers.add(nums[j]);
                    integers.add(nums[k]);
                    lists.add(integers);
                }
            }
        }
        return lists;
    }
}
