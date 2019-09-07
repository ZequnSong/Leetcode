# Set Matrix Zeroes

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it **in-place**.

**Example:**
```
Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
```
思路：空间复杂度O(m+n)

* 创建两个数组row和col，记录列方向和行方向是否为0
* 遍历matrix，遇到0就在row和col数组对应位置做标记
* 根据row和col将matrix对应行列变为0
```
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                if(matrix[i][j] == 0){
                    row[i] = true;
                    col[j] = true;
                }
        
        for(int i = 0; i < m; i++)
            if(row[i] == true)
                for(int j = 0; j < n; j++)
                    matrix[i][j] = 0;
            
        for(int i = 0; i < n; i++)
            if(col[i] == true)
                for(int j = 0; j < m; j++)
                    matrix[j][i] = 0;       
    }
}
```

思路：空间复杂度O(1)

不能新建数组，用原数组的第一行第一列来记录各行各列是否有0.

- 先扫描第一行第一列，如果有0，则将各自的flag设置为true
- 然后扫描除去第一行第一列的整个数组，如果有0，则将对应的第一行和第一列的数字赋0
- 再次遍历除去第一行第一列的整个数组，如果对应的第一行和第一列的数字有一个为0，则将当前值赋0
- 最后根据第一行第一列的flag来更新第一行第一列
```
class Solution {
    public void setZeroes(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return;
        
        boolean rowZero = false, colZero = false;
        for(int i = 0; i < matrix.length; i++)
            if(matrix[i][0] == 0){
                colZero = true;
                break;
            }
        for(int i = 0; i < matrix[0].length; i++)
            if(matrix[0][i] == 0){
                rowZero = true;
                break;
            }
        
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        
        for(int i = 1; i < matrix.length; i++)
            if(matrix[i][0] == 0)
                for(int j = 1; j < matrix[0].length; j++)
                    matrix[i][j] = 0;
    
        
        for(int i = 1; i < matrix[0].length; i++)
            if(matrix[0][i] == 0)
                for(int j = 1; j < matrix.length; j++)
                    matrix[j][i] = 0;
            
        if(rowZero)
            for(int j = 0; j < matrix[0].length; j++)
                    matrix[0][j] = 0;
        if(colZero)
            for(int j = 0; j < matrix.length; j++)
                    matrix[j][0] = 0;
    }
}
```
