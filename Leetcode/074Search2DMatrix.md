# Search a 2D Matrix

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

**Example 1:**
``
Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
```


```
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int rowUp = 0;
        int rowDn = m-1;
        if(target < matrix[rowUp][0]) return false;
        while(rowUp < rowDn){
            int mid = rowUp + (rowDn - rowUp+1)/2;
            if(matrix[mid][0] == target) return true;
            if(matrix[mid][0] < target)
                rowUp = mid;
            else
                rowDn = mid-1;
        }
        int colLf = 0;
        int colRt = n-1;
        while(colLf <= colRt){
            int mid = colLf + (colRt - colLf)/2;
            if(matrix[rowUp][mid] == target) return true;
            if(matrix[rowUp][mid] > target)
                colRt = mid-1;
            else
                colLf = mid+1;
        }
        return false;
        
    }
}
```
