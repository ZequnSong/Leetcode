# Sqrt(x)

Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

**Example 1:**
```
Input: 4
Output: 2
```
**Example 2:**
```
Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
```
二分法

**注意判断时用mid > x/mid 而不是 mid*mid > x, 以为可能x为mid*mid可能越界溢出造成无法比较大小**
```
class Solution {
    public int mySqrt(int x) {
        if(x==0) return 0;
        int left = 1, right = x;
        while(left + 1 < right){
            int mid = left + (right - left)/2;
            if(mid == x/mid) return mid;
            if(mid < x/mid) 
                left = mid;
            else 
                right = mid;
        }
        if(right <= x/right) return right;
        else return left;
    }
}
```
