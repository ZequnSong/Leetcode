# N Queens

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

**Example:**
```
Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
```

**思路：简单回溯**

一行一行的来放置queen，每个queen有n个可能的位置：0到n-1

* 先建立一个长度为 nxn 的全是点的数组 board，然后从第0行(第一个queen)开始调用递归。
* 在递归函数中，首先判断当前行数是否已经为n，是的话，说明n个皇后都已经成功放置好了，即当前一个assignment已经完成，只要将 board 数组加入结果 res 中即可。
* 否则的话，遍历该行queen的值域(0,n-1)，行跟列的位置都确定后，验证当前位置是否会产生冲突，使用一个子函数来判断了

* 判断冲突子函数：
* 首先验证该列是否有冲突，遍历之前的所有行，若某一行相同列也有皇后，则冲突返回false；
* 再验证两个对角线是否冲突，若都没有冲突，则说明该位置可以放皇后，放了新皇后之后，再对下一行调用递归即可，注意递归结束之后要返回状态


```
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                board[i][j] = '.';
            }
        }
        helper(0, board, res);
        return res;
    }
    
    public void helper(int curRow, char[][] board, List<List<String>> res){
        int n = board.length;
        //if assignment is completed
        if(curRow == n){
            List<String> assignment = new ArrayList<>();
            for(int i = 0; i < n; i++){
                assignment.add(String.valueOf(board[i]));
            }
            res.add(assignment);
            return;
        }
        
        //select unassigned variable (Queen in curRow)
        //for each value in varibale's domain
        for(int i = 0; i < n; i++){
            //if value is consistent
            if(isConsistent(board, curRow, i)){
                board[curRow][i] = 'Q';
                helper(curRow+1, board, res);
                board[curRow][i] = '.';
            }
        }
    }
    
    boolean isConsistent(char[][] board, int row, int col){
        //check if two Queens in same column
        for(int i = 0; i < row; i++)
            if(board[i][col] == 'Q') return false;
        
        //check left diagonal
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if(board[i][j] == 'Q') return false;
        
        //check right diagonal
        for(int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++)
            if(board[i][j] == 'Q') return false;
        
        return true;
        
    }
}
```

**思路2： CSP**
