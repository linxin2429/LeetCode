package com.xldeng.solution1_20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created on 2020/9/2.
 *
 * @author xldeng
 */
public class Solution17 {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        HashMap<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "uvw");
        map.put('9', "xyz");
        backtrack(combinations, map, digits, 0, new StringBuilder());
        return combinations;
    }

    private void backtrack(List<String> combinations, HashMap<Character, String> map, String digits, int index,
                           StringBuilder combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = map.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, map, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }
}
