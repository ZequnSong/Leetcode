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

验证对角线是否冲突时，只需验证当前row之前的Q点即可，因为此时当前row之后还没放Q，因此for循环时可令i=row-1

也可用坐标变换数组，更省事，但是会有很多不必要计算，耗时长


```
class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        res = []
        if not n or n <= 0:
            return res
        board = [["." for _ in range(n)] for _ in range(n)]
        def dfs(rowIndex):
            nonlocal n
            if rowIndex == n:
                res.append([ "".join(board[i]) for i in range(n)])
                return

            for i in range(n):
                if not self.isValid(board,rowIndex,i,n):
                    continue
                board[rowIndex][i] = "Q"
                dfs(rowIndex+1)
                board[rowIndex][i] = "."
        dfs(0)
        return res

    def isValid(self,board,row,col,n):
        # column check
        for index in range(row):
            if board[index][col] == "Q":
                return False
                
        # diagonal check
        cur_x,cur_y = row-1,col-1
        while 0<=cur_x and 0<=cur_y:
            if board[cur_x][cur_y] == "Q":
                return False
            cur_x-=1
            cur_y-=1
            
        cur_x,cur_y = row-1,col+1
        while 0<=cur_x and cur_y<n:
            if board[cur_x][cur_y] == "Q":
                return False
            cur_x-=1
            cur_y+=1

        return True
```

**思路2： CSP**
