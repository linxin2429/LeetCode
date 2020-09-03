package com.xldeng.solution1_20;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created on 2020/9/2.
 *
 * @author xldeng
 */
public class Solution20 {
    public boolean isValid(String s) {
        int len = s.length();
        if (len == 0) {
            return true;
        }
        if ((len % 2) == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (stack.isEmpty() || !stack.peek().equals(map.get(c))) {
                    return false;
                }
                stack.pop();

            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
