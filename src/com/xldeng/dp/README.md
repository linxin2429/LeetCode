跟随labuladong的算法小抄刷题  
地址：  
https://labuladong.gitbook.io/algo/
动态规划
## 509 [斐波那契数](https://leetcode-cn.com/problems/fibonacci-number/)  
动态规划解答
```java
class Solution {
    public int fib(int N) {
        if(N < 1){
            return 0;
        }
        if(N == 1 || N == 2){
            return 1;
        }
        int[] dp = new int[2];
        dp[0] = dp[1] = 1;
        int sum = 0;
        for(int i = 2; i < N; i++){
            sum = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = sum;
        } 
        return dp[1];
    }
}
```
状态压缩动态规划
```java
class Solution {
    public int fib(int N) {
        if(N < 1){
            return 0;
        }
        int prev =1 , curr = 1;
        for(int i = 2; i < N; i++){
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        } 
        return curr;
    }
}
```

## 322 [零钱兑换](https://leetcode-cn.com/problems/coin-change/)
```markdown
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1
```
```java
class Solution{
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        if (coins == null || coins.length == 0) {
            return -1;
        }
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (Integer coin : coins) {
                if (i < coin) continue;
                if (dp[i - coin] < 0 || dp[i - coin] > min) continue;
                min = dp[i - coin];
            }
            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }
        }
        return dp[amount];
    }
}
```