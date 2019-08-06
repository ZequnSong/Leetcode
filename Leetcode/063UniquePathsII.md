# Unique Paths II

类似 [Unique Paths](https://github.com/ZequnSong/Leetcode/blob/master/Leetcode/062UniquePaths.md)

求从左上到达最右下角的所有不同走法的个数, 多了障碍物限制，1代表障碍，0代表畅通

思路：网格问题 -- DP

* dp[i][j] 表示从左上开始，到第i行第j列的路径数目
* 初始化最上行和最左列(当i=0或j=0),路径畅通时为1，当遇到障碍时跳出(该位置和后面都是0)
* 之后的递推式：畅通时，dp[i][j] = dp[i-1][j] + dp[i][j-1], 当遇到障碍时 dp[i][j] = 0

```
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int i = 1; i < m; i++){
            if(obstacleGrid[i][0] == 0)
                dp[i][0] = 1;
            else
                break;
        }
        for(int i = 1; i < n; i++){
            if(obstacleGrid[0][i] == 0)
                dp[0][i] = 1;
            else
                break;
        }
        
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(obstacleGrid[i][j] == 1)
                    dp[i][j] = 0;
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
```
