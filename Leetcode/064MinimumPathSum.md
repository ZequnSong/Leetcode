# Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

**Note:** You can only move either down or right at any point in time.

**Example:**
```
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
```

思路： DP

* dp[i][j] 表示到达第i行第j列的最小Sum
* 由于只能向右或向下走，所以最上面行和最左面列(当i=0或j=0)的Sum可以直接初始化
* 之后的递推式：dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j]

```
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < m; i++)
            dp[i][0] = dp[i-1][0] + grid[i][0];
        for(int i = 1; i < n; i++)
            dp[0][i] = dp[0][i-1] + grid[0][i];
    
        for(int i = 1; i < m; i++)
            for(int j = 1; j < n; j++)
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];

        return dp[m-1][n-1];
    }
}
```
