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
