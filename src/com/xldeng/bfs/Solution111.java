package com.xldeng.bfs;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Created on 2020/9/4.
 *
 * @author xldeng
 */
public class Solution111 {
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


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

