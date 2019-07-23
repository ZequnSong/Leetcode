# Rotate Image

You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).

**Note:**
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

**Example:**
```
Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
```

**思路：**

* 对于当前位置matrix[i][j]，计算旋转后的新位置，然后再计算下一个新位置，第四个位置又变成当前位置了，每次循环换四个数字，如下所示：
![](/pictures/question_48.jpeg)

* 每遍历一次i，都会旋转当前所在的一环

```
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;      
        for(int i = 0; i <= n/2-1; i++){
            for(int j = i; j <= n-i-2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }
}
```
