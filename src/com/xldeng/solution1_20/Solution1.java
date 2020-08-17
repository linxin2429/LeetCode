package com.xldeng.solution1_20;

import java.util.HashMap;

/**
 * @ClassName: Solution1
 * @Description: 两数之和
 * @Author: xldeng
 * @Date: 2020/8/17 12:35
 * @Version: 1.0
 **/
public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        //使用HashMap，key为nums[i],value为i
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //比较已存入HashMap的key中是否存在要求的值target - nums[i]
            //若存在，返回两数在nums中的索引  同时避免判断是否有重复的索引
            if (hashMap.containsKey(target - nums[i])){
                return new  int[]{hashMap.get(target - nums[i]),i};
            }
            hashMap.put(nums[i],i);
        }
        return null;
    }
}