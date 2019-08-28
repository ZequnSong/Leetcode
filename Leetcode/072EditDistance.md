# Edit Distance

Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

1. Insert a character
2. Delete a character
3. Replace a character

**Example 1:**
```
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
```

* dp[i][j] 表示 word1 的前i个字符转换到 word2 的前j个字符所需要的步骤
* 先给dp的第一行第一列赋值
* 当word1.charAt(i-1) == word2.charAt(j-1)时，dp[i][j] = dp[i - 1][j - 1]
* 当word1.charAt(i-1) 不等于 word2.charAt(j-1)时，需要考虑插入删除替换三种操作：
  * 例如 abd 转换成 bc 的次数为 dp[3][2]
  * 插入：在abd后插入c以便与word2中第j个字符c匹配：所以需要先匹配abd和b，此时次数为 dp[3][2-1]，再在abd后插入c，次数为dp[3][2-1] + 1
  * 删除：将abd中第i个字符d删掉以便与word2匹配，所以变成了ab 转换成 bc, 此时次数为 dp[3-1][2], 加上删除d的一步, 次数为dp[3-1][2] + 1
  * 替换: 将abd中第i个字符转换成bc中第j个字符，为了保证匹配，这要求ab和b是匹配的，此时次数为 dp[3-1][2-1]，再加上替换的一步，次数为 dp[3-1][2-1] + 1

```
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i < m + 1; i++) dp[i][0] = i;
        for(int j = 0; j < n + 1; j++) dp[0][j] = j;
        for(int i = 1; i < m + 1; i++){
            for(int j = 1; j < n + 1; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]))+1;
            }
        }
        return dp[m][n];
    }
}
```
