# Spiral Matrix

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

**Example 1:**

```
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
```

思路：

* 将矩阵按照螺旋顺序打印出来
* 每次循环，打印四条边一圈(layer)，对每个环的边按顺序打印
 


```
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length == 0) return new ArrayList<Integer>();
        List<Integer> res = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        int layer = 0;       
        while((n-2*layer) > 0 && (m-2*layer) > 0){
            for(int i = layer; i <= n - 1 - layer; i++)
                res.add(matrix[layer][i]);
            
            for(int i = layer + 1; i <= m - 1 - layer; i++)
                res.add(matrix[i][n - 1 - layer]);
            
            if(m-1-layer != layer){
                for(int i = n - 1 - layer  - 1; i >= layer; i --)
                    res.add(matrix[m - 1 - layer][i]);
            }
            
            if(n-1-layer != layer){
                for(int i = m - 1 - layer - 1; i >= layer + 1; i--)
                    res.add(matrix[i][layer]);
            }         
            layer++;
        }        
        return res;
    }
}
```
