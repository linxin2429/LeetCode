package com.xldeng.bfs;

import java.util.*;

/**
 * Created on 2020/9/4.
 *
 * @author xldeng
 */
public class Solution752 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.openLock(new String[]{"0203"}, "1000");
    }

    public int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        // 把deadends元素给visited初始化，更优雅
        HashSet<String> visited = new HashSet<>(Arrays.asList(deadends));
        int ans = 0;
        String root = "0000";
        if (visited.contains(root)) {
            return -1;
        }
        queue.offer(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (Objects.equals(target, poll)) {
                    return ans;
                }
                for (int j = 0; j < 4; j++) {
                    String up = up(poll, j);
                    if (!(visited.contains(up))) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down = down(poll, j);
                    if (!(visited.contains(down))) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    private String up(String code, int j) {
        char[] chars = code.toCharArray();
        if (chars[j] == '9') {
            chars[j] = '0';
        } else {
            chars[j]++;
        }
        return new String(chars);
    }

    private String down(String code, int j) {
        char[] chars = code.toCharArray();
        if (chars[j] == '0') {
            chars[j] = '9';
        } else {
            chars[j]--;
        }
        return new String(chars);
    }
}

class Solution {
    public int openLock(String[] deadends, String target) {
        HashSet<String> deads = new HashSet<>(Arrays.asList(deadends));
        HashSet<String> q1 = new HashSet<>();
        HashSet<String> q2 = new HashSet<>();
        HashSet<String> visited = new HashSet<>();

        int ans = 0;
        String root = "0000";
        if (deads.contains(root)) {
            return -1;
        }
        q1.add(root);
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            HashSet<String> temp;
            if (q1.size() > q2.size()) {
                temp = q1;
                q1 = q2;
                q2 = temp;
            }
            temp = new HashSet<>();
            for (String s : q1) {
                if (deads.contains(s)){
                    continue;
                }
                if (q2.contains(s)) {
                    return ans;
                }
                visited.add(s);
                for (int i = 0; i < 4; i++) {
                    String up = up(s, i);
                    if (!(visited.contains(up))) {
                        temp.add(up);
                    }
                    String down = down(s, i);
                    if (!(visited.contains(down))) {
                        temp.add(down);
                    }
                }
            }
            ans++;
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }

    private String up(String code, int j) {
        char[] chars = code.toCharArray();
        if (chars[j] == '9') {
            chars[j] = '0';
        } else {
            chars[j]++;
        }
        return new String(chars);
    }

    private String down(String code, int j) {
        char[] chars = code.toCharArray();
        if (chars[j] == '0') {
            chars[j] = '9';
        } else {
            chars[j]--;
        }
        return new String(chars);
    }
}
