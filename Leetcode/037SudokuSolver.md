# Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

---
方法1 ：思路：DFS递归
类似题目有 Permutations 全排列，Combinations 组合项， N-Queens N皇后问题等

* 对于每个需要填数字的格子带入1到9，每代入一个数字都判定其是否合法
  * 如果合法就继续下一次递归
  * 若不合法，把该位置重置为'.'，回溯继续dfs

```
class Solution {
    public void solveSudoku(char[][] board) {
        solveDfs(board, 0, 0);
    }
    boolean solveDfs(char[][] board, int i, int j){
        if(i >= 9) return true;
        if(j >= 9) return solveDfs(board, i+1, 0);
        if(board[i][j] == '.'){
            for(int k = 1; k <= 9; k++){
                board[i][j] = (char)(k + '0');
                if(isValid(board, i, j))
                    if(solveDfs(board, i, j+1))
                        return true;
                board[i][j] = '.';
            }
        }else
            return solveDfs(board, i, j + 1);
        return false;
    }
    boolean isValid(char[][] board, int i, int j){
        for(int col = 0; col < 9; col++)
            if(col != i && board[i][j] == board[col][j])
                return false;
        for(int row = 0; row < 9; row++)
            if(row != j && board[i][j] == board[i][row])
                return false;
        for(int row = 3*(i/3); row < 3*(i/3) + 3; row++)
            for(int col = 3*(j/3); col < 3*(j/3) + 3; col++)
                if((row != i || col != j) && board[i][j] == board[row][col])
                    return false;        
        return true;  
    }
}
```
方法二： CSP

```
class Solution {
    public void solveSudoku(char[][] board) {
    // initialize objects
    Cell[][] cells = new Cell[board.length][board[0].length];
    // initialize board
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[0].length; j++) {
        cells[i][j] = new Cell(i, j);
      }
    }

    // number of empty grid we need to fill
    int space = 0;
    // assign value
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[0].length; j++) {
        cells[i][j].val = board[i][j];
        if (board[i][j] != '.') {
          updateRemove(cells, cells[i][j]);
        } else {
          space++;
        }
      }
    }
    System.out.println(csp(board, cells, space));
  }

  public boolean csp(char[][] board, Cell[][] cells, int space) {

    // chech if assignment is completed
    if (space == 0) {
      return true;
    }
      
    // select an unassigned variable with MRV order
    Cell min = findMin(cells);

    // for each value in that variable, loop through, can use LCV to improve
    Set<Character> ava = new HashSet<>();
    ava.addAll(min.available);
    for (char s : ava) {
      min.val = s;
      Set<Cell> removed = updateRemove(cells, min);
    // if consistent, assign the value and do recurse
      if (csp(board, cells, space - 1)) {
        board[min.i][min.j] = min.val;
        return true;
      }
      // if doesn't work then reset, backtrack and choose another
      updateAdd(removed, min);
    }

    return false;
  }

// Filtering: when assign a value to a variable cell, remove the values of other variables that violate the constraint
  public Set<Cell> updateRemove(Cell[][] cells, Cell cell) {
    // variables that need to be updated, because cannot have the cell.val
    Set<Cell> result = new HashSet<>();
     
    for (int i = 0; i < cells.length; i++) {
        //check for column
      if (cells[i][cell.j].remove(cell.val)) {
        result.add(cells[i][cell.j]);
      }
        //check for row
      if (cells[cell.i][i].remove(cell.val)) {
        result.add(cells[cell.i][i]);
      }
    }
    int i_c = cell.i / 3;
    int j_c = cell.j / 3;
    // remove the val for the thing
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (cells[i_c * 3 + i][j_c * 3 + j].remove(cell.val)) {
          result.add(cells[i_c * 3 + i][j_c * 3 + j]);
        }
      }
    }
    return result;
  }
    
// before backtracking, reset the variables' domains we have changed
  public void updateAdd(Set<Cell> cells, Cell cell) {
    for (Cell c : cells) {
      c.add(cell.val);
    }

    cell.val = '.';
  }

// Ordering: MRV -- Minimum Remaining Values
// chose the variable with the fewest legal left values in its domain
// all variables have same connect degree, threrefore, degree heuristic is not useful here
  public Cell findMin(Cell[][] cells) {
    Cell result = new Cell(-1, -1);
    for (int i = 0; i < cells.length; i++) 
      for (int j = 0; j < cells[0].length; j++) 
        if (cells[i][j].isEmpty() && result.size() > cells[i][j].size()) 
          result = cells[i][j];
    return result;
  }
  
// variables defination for each grid
  private class Cell {
    public Character val;
    public Set<Character> available; //domains
    public int i;
    public int j;

    public Cell(int i, int j) {
      this.i = i;
      this.j = j;
      this.available = new HashSet<>();
      for (char n = '1'; n <= '9'; n++) {
        available.add(n);
      }
    }

    public int size() {
      return this.available.size();
    }

    public boolean remove(Character c) {
      return available.remove(c);
    }

    public boolean add(Character c) {
      return available.add(c);
    }

    @Override
    public String toString() {
      return val + " ";
    }

    public boolean isEmpty() {
      return val == '.';
    }
  }
}
```
