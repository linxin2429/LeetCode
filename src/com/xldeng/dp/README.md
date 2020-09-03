跟随labuladong的算法小抄刷题  
地址：  
https://labuladong.gitbook.io/algo/
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