package com.xldeng.backtrack;


import java.util.LinkedList;
import java.util.List;

/**
 * Created on 2020/9/3.
 *
 * @author xldeng
 */
public class Solution46 {
    List<List<Integer>> lists = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        backtrack(nums, new LinkedList<>());
        return lists;
    }

    private void backtrack(int[] nums, LinkedList<Integer> list) {
        if (list.size() == nums.length) {
            lists.add(new LinkedList<>(list));
            return;
        }
        for (int num : nums) {
            if (list.contains(num)) {
                continue;
            }
            list.add(num);
            backtrack(nums, list);
            list.removeLast();
        }
    }
}
