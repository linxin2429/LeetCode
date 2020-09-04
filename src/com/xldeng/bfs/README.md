BFS  
## 111 [二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int minDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int ans = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return ans;
                } else {
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
            ans++;
        }
        return ans;
    }
}
```

## 752 [打开转盘锁](https://leetcode-cn.com/problems/open-the-lock/)
```java
public class Solution {
    public int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        // 把deadends元素给visited初始化，更优雅
        HashSet<String> visited = new HashSet<>(Arrays.asList(deadends));
        int ans = 0;
        String root = "0000";
        if(visited.contains(root)){
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
```
双向BFS
```java
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
```