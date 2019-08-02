# N Queens II

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

**Example:**
```
Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
```


思路： 对 [051 N Queens](https://github.com/ZequnSong/Leetcode/blob/master/Leetcode/051N-Queens.md) 稍加改动

```
class Solution {
    public int totalNQueens(int n) {
        int res = 0;
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = '.';
            }
        }
        return helper(0, board, res);
    }
    
    public int helper(int curRow, char[][] board, int res){
        int n = board.length;
        if(curRow == n){
            res++;
            return res;
        }
        
        for(int i = 0; i < n; i++){
            if(isConsistent(curRow, i, board)){
                board[curRow][i] = 'Q';
                res = helper(curRow+1, board, res);
                board[curRow][i] = '.';      
            }
        }
        return res;
    }
    
    public boolean isConsistent(int row, int col, char[][] board){
        for(int i = 0; i < row; i++)
            if(board[i][col] == 'Q') return false;
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if(board[i][j] == 'Q') return false;
        for(int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++)
            if(board[i][j] == 'Q') return false;
        return true;
    }
}
```
