package com.xldeng.dp;

import java.util.Arrays;

/**
 * Created on 2020/9/3.
 *
 * @author xldeng
 */
public class Solution322 {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int dpMin = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i < coins[j]) {
                    continue;
                }
                int dpCurr = dp[i - coins[j]];
                if (dpCurr >= dpMin || dpCurr < 0) {
                    continue;
                }
                dpMin = dpCurr;
            }
            if (dpMin == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = dpMin + 1;
            }
        }
        return dp[amount];
    }


}

