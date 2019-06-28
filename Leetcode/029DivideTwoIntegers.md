# Divide Two Integers
Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

**Example 1:**
```
Input: dividend = 10, divisor = 3
Output: 3
```
**Example 2:**
```
Input: dividend = 7, divisor = -3
Output: -2
```
**Note:**
Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.

Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 ^− 1]. For the purpose of this problem, assume that your function returns 2^31 ^− 1 when the division result overflows.

**思路： 位运算**  若只是单纯循环减去除数，会超时

* 先处理边界情况，32-bit signed integer range: [−2^31,  2^31 ^− 1], 意味着当dividend取最小值-2^31，divisor取-1时，会产生溢出，此外若divisor为0也会溢出，此种情况直接返回int最大值
* dividend和divisor取绝对值，并记录正负号symbol
* 当dividend大于divisor时
  * 若dividend大于divisor的2倍，记下2个计数，并再次扩大divisor为2倍，重复判断
  * 将计数加入res中，从dividend中减去扩大后的divisor的值
* 结合符号位输出res
```
class Solution {
    public int divide(int dividend, int divisor) {
        if(divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;
        long m = Math.abs((long)dividend), n = Math.abs((long)divisor);
        
        int sybmol = (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0) ? 1 : -1;
        
        int res = 0;
        
        while(m >= n){
            long curDivisor = n, p = 1;
            while( m >= (curDivisor<<1)){
                curDivisor <<= 1;
                p <<= 1;
            }
            res += p;
            m -= curDivisor;
        }
         
        return sybmol > 0 ? res : -res;
    }
}
```
