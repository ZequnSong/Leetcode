# Unique Paths

<img src="/pictures/question_62.png" width="300">

求从左上到达最右下角的所有不同走法的个数

思路：网格问题 -- DP

* dp[i][j] 表示从左上开始，到第i行第j列的路径数目
* 由于只能向右或向下走，所以最上面行和最左面列(当i=0或j=0)的路径数都只能为1
* 之后的递推式：dp[i][j] = dp[i-1][j] + dp[i][j-1]
```
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || j == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
```
