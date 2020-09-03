回溯  
## 46 [全排列](https://leetcode-cn.com/problems/permutations/)  
```java
class Solution {
    List<List<Integer>> lists = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        backtrack(nums,new LinkedList<>());
        return lists;
    }

    private void backtrack(int[] nums, LinkedList<Integer> list) {
        if (list.size() == nums.length) {
            lists.add(new LinkedList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            backtrack(nums, list);
            list.removeLast();
        }
    }
}
```

## 51 [N皇后问题](https://leetcode-cn.com/problems/n-queens/solution/nhuang-hou-by-leetcode-solution/)
```java
public class Solution51 {
    List<List<String>> lists = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens,-1);
        Set<Integer> columnSet = new HashSet<>();
        Set<Integer> diagSet1 = new HashSet<>();
        Set<Integer> diagSet2 = new HashSet<>();

        backtrack(queens, n, 0, columnSet, diagSet1, diagSet2);
        return lists;
    }

    private void backtrack(int[] queens, int n, int row, Set<Integer> columnSet, Set<Integer> diagSet1,
                           Set<Integer> diagSet2) {
        if (row == n) {
            lists.add(getBoard(queens));
        }else {
            for (int i = 0; i < n; i++) {
                if (columnSet.contains(i)){
                    continue;
                }
                if (diagSet1.contains(i + row)){
                    continue;
                }
                if (diagSet2.contains(i - row)){
                    continue;
                }
                queens[row] = i;
                columnSet.add(i);
                diagSet1.add(i + row);
                diagSet2.add(i - row);
                backtrack(queens,n,row + 1,columnSet,diagSet1,diagSet2);
                queens[row] = -1;
                columnSet.remove(i);
                diagSet1.remove(i+row);
                diagSet2.remove(i-row);
            }
        }
    }

    private List<String> getBoard(int[] queens) {
        int n = queens.length;
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row,'.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
```
位运算



