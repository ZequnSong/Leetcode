# Valid Sudoku

Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated **according to the following rules:**

1. Each row must contain the digits 1-9 without repetition.
2. Each column must contain the digits 1-9 without repetition.
3. Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

---

**思路：**

验证一个方阵是否为数独矩阵，判断标准是看各行各列以及每个小的3x3的小方阵里面是否有重复数字

如果都无重复，则当前矩阵是数独矩阵，但不代表待数独矩阵有解，只是单纯的判断当前未填完的矩阵是否是数独矩阵

遍历每个数字的时候，就看看包含当前位置的行和列以及3x3小方阵中是否已经出现该数字

需要三个标志矩阵，分别记录各行，各列，各小方阵是否出现某个数字，其中行和列标志下标很好对应，就是小方阵的下标需要稍稍转换一下
```
class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean [][] row = new boolean [9][9];
        boolean [][] col = new boolean [9][9];
        boolean [][] sqr = new boolean [9][9];
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){               
                if(board[i][j] >= '1' && board[i][j] <= '9'){              
                    int temp = (int)board[i][j]-(int)'0'-1;
                    if(!row[i][temp] && !col[j][temp] && !sqr[3*(i/3)+j/3][temp]) {
                        row[i][temp] = true;
                        col[j][temp] = true;
                        sqr[3*(i/3)+j/3][temp] = true;
                    }else
                        return false;
                }
            }
        }
        return true;
    }
}
```
