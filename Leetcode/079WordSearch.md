# Word Search

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

**Example:**
```
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
```

思路：DFS

类似迷宫问题，每个点有上下左右四个方向

* 由于每个cell只能使用一次，所以需要建立isUsed二维数组
* 以board中每个点都作为起点分别调用dfs
* dfs内部对当前节点的上下左右四个方向分别调用dfs，当有一个返回true则说明找到

```
class Solution {
    public boolean exist(char[][] board, String word) {
        if(board.length == 0 || board[0].length == 0) return false;
        if(word.length() == 0) return true;    
        boolean[][] isUsed = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(dfs(board, isUsed, 0, i, j, word))
                    return true;
            }
        }
        return false;
    }
    
    boolean dfs(char[][] board, boolean[][] isUsed, int count, int i, int j, String word){
        if(count == word.length()) return true;
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || isUsed[i][j] || board[i][j] != word.charAt(count)) return false;

        isUsed[i][j] = true;
        count++;
        boolean res = dfs(board, isUsed, count, i-1, j, word)
            || dfs(board, isUsed, count, i, j+1, word)
            || dfs(board, isUsed, count, i+1, j, word)
            || dfs(board, isUsed, count, i, j-1, word);
        isUsed[i][j] = false;
        
        return res;
    }
    
}
```
