# Reverse Integer

Given a 32-bit signed integer, reverse digits of an integer.

**Example 1:**
```
Input: 123
Output: 321
```
**Example 2:**
```
Input: -123
Output: -321
```
**Example 3:**
```
Input: 120
Output: 21
```

**Note:**
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

O(n)思路： 翻转数字

* 溢出问题
  int型的数值范围是  [−2^31,  2^31 − 1]，-2147483648～2147483647
  如果翻转 1000000009，得到 9000000001，翻转后的数就超过了范围
  检验res是否大于Integer.MAX_VALUE/10
  * 如果大于，则res = res*10 +x%10必溢出
  * 如果小于，则res = res*10 +x%10不溢出，因为输入的x也是一个整型数，所以x的范围也应该在 -2147483648～2147483647 之间，那么x的第一位只能是1或者2，翻转之后 res 的最后一位只能是1或2，所以 res 只能是 2147483641 或 2147483642 都在正确的范围内，所以不用 check
  
* 正负号：不需特意处理正负号，不影响计算


```
class Solution {
    public int reverse(int x) {
        int res = 0;
        while(x != 0){
            if(Math.abs(res) > Integer.MAX_VALUE/10)
                return 0;
            res = res*10 +x%10;
            x = x/10;
        }      
        return  res;
    }
}
```
