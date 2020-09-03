package com.xldeng.backtrack;

import java.util.*;

/**
 * Created on 2020/9/3.
 *
 * @author xldeng
 */
public class Solution51 {
    List<List<String>> lists = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        boolean[] column = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1];
        boolean[] diag2 = new boolean[2 * n - 1];

        backtrack(queens, n, 0, column, diag1, diag2);
        return lists;
    }

    private void backtrack(int[] queens, int n, int row, boolean[] column, boolean[] diag1, boolean[] diag2) {
        if (row == n) {
            lists.add(getBoard(queens));
        } else {
            for (int i = 0; i < n; i++) {
                if (column[i] || diag1[i] || diag2[i]) {
                    continue;
                }
                queens[row] = i;
                column[i] = true;
                diag1[row + i] = true;
                diag2[row - i + n - 1] = true;

                backtrack(queens, n, row + 1, column, diag1, diag2);
                queens[row] = -1;
                column[i] = false;
                diag1[row + i] = false;
                diag2[row - i + n - 1] = false;
            }
        }
    }

    private List<String> getBoard(int[] queens) {
        int n = queens.length;
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}

class Solution {
    List<List<String>> lists = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);


        backtrack(queens, n, 0, 0, 0, 0);
        return lists;
    }

    private void backtrack(int[] queens, int n, int row, int columns, int diag1, int diag2) {
        if (row == n) {
            lists.add(getBoard(queens));
        } else {

            int availablePositions = ((1 << n) - 1) & (~(columns | diag1 | diag2));
            while (availablePositions != 0) {
                // x & (−x) 可以获得 x 的二进制表示中的最低位的 1 的位置；
                int position = availablePositions & (-availablePositions);
                // x & (x−1) 可以将 x 的二进制表示中的最低位的 1 置成 0,循环结束时availablePositions==0
                availablePositions = availablePositions & (availablePositions - 1);
                int column = Integer.bitCount(position - 1);
                queens[row] = column;
                backtrack(queens, n, row + 1, columns | position, (diag1 | position) << 1,
                        (diag2 | position) >> 1);
                queens[row] = -1;
            }
        }
    }

    private List<String> getBoard(int[] queens) {
        int n = queens.length;
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}