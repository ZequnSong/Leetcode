# Spiral Matrix II

Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

思路： 类似 [Spiral Matrix](https://github.com/ZequnSong/Leetcode/blob/master/Leetcode/054SpiralMatrix.md)
```
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int layer = n/2 + 1;
        int count = 0;
        int value = 1;
        while(count < layer){
            for(int i = count; i <= n - 1 - count; i++)
                res[count][i] = value++;
            for(int i = count + 1; i <= n - 1 - count; i++)
                res[i][n-1-count] = value++;
            for(int i = n - 1 - count - 1; i >= count; i--)
                res[n-1-count][i] = value++;
            for(int i = n - 1 - count - 1; i >= count + 1; i--)
                res[i][count] = value++;
            count++;
        }
        return res;
        
    }
}
```
