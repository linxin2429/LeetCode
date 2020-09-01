package com.xldeng.solution1_20;


/**
 * Created on 2020/9/1.
 *
 * @author xldeng
 */
public class Solution6 {
    public String convert(String s, int numRows) {
        int len = s.length();
        // 行数为1 或者 s字符串长度小于行数，s不会变,直接返回s就行
        if (numRows == 1 || len <= numRows) {
            return s;
        }
        // z字型排列每行都会产生一个字符串，共numRows个
        StringBuilder[] rows = new StringBuilder[numRows];
        // 初始化
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }
        // 当前行号索引
        int rowIndex = 0;
        // 判断是否向下
        boolean down = false;
        for (char c : s.toCharArray()) {
            rows[rowIndex].append(c);
            // rowIndex在numRows - 1 和 0 之间变动，到达某一个端点时，将down取反
            if (rowIndex == numRows - 1 || rowIndex == 0) {
                down = !down;
            }
            // down为true rowIndex递增，为false就递减
            rowIndex += down ? 1 : -1;
        }
        // 将rows合并
        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) {
            ans.append(row.toString());
        }
        return ans.toString();
    }
}
