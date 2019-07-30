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
思路1：

* 并不需要知道每一行皇后的具体位置，而只需要知道会不会产生冲突即可。
* 对于每行要新加的位置，需要看跟之前的列，对角线，及逆对角线之间是否有冲突，所以我们需要三个布尔型数组，分别来记录之前的列 col，对角线 diag1，及逆对角线 diag2上的位置，其中 cols 初始化大小为n，diag 和 anti_diag 均为 2n。
* 列比较简单，是当前queen位置属于哪列就直接去 cols 中查找，
* 对角线的话，仔细观察数组位置坐标，可以发现所有同一条主对角线的数，其纵坐标减去横坐标再加n，一定是相等的。同理，同一条逆对角线上的数字，其横纵坐标之和一定是相等的，根据这个，就可以快速判断主逆对角线上是否有冲突。
* 任意一个有冲突的话，直接跳过当前位置，否则对于新位置，三个数组中对应位置都赋值为 true，然后对下一行调用递归，递归返回后记得还要还原状态，

```
class Solution {
    public int totalNQueens(int n) {
        int res = 0;
        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[2*n];
        boolean[] diag2 = new boolean[2*n];
        
        return helper(0, col, diag1, diag2, res);
    }
    
    public int helper(int curRow, boolean[] col, boolean[] diag1, boolean[] diag2, int res){
        if(curRow == col.length) return ++res;
        for(int i = 0; i < col.length; i++){
            if(col[i] || diag1[curRow - i + col.length - 1] || diag2[curRow + i]) continue;
            col[i] = true;
            diag1[curRow - i + col.length - 1] = true;
            diag2[curRow + i] = true;
            
            res = helper(curRow+1, col, diag1, diag2, res);
            
            col[i] = false;
            diag1[curRow - i + col.length - 1] = false;
            diag2[curRow + i] = false;
            
        }
        return res;
    }
}
```



---

思路2： 对 [051 N Queens](https://github.com/ZequnSong/Leetcode/blob/master/Leetcode/051N-Queens.md) 稍加改动

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
