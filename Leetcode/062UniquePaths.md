# Unique Paths

<img src="/pictures/question_62.png" width="300">

求从左上到达最右下角的所有不同走法的个数

思路：网格问题 -- DP

* dp[i][j] 表示从左上开始，到第i行第j列的路径数目
* 由于只能向右或向下走，所以最上面行和最左面列(当i=0或j=0)的路径数都只能为1
* 之后的递推式：dp[i][j] = dp[i-1][j] + dp[i][j-1]
```
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        grid = [[0 for _ in range(n)] for _ in range(m)]
        grid[0][0] = 1
        for i in range(m):
            grid[i][0] = 1
        for j in range(n):
            grid[0][j] = 1
        for i in range(1,m):
            for j in range(1,n):
                grid[i][j] = grid[i-1][j] + grid[i][j-1]
        return grid[m-1][n-1]
        
```
