# Palindrome Number

Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

**Example 1:**
```
Input: 121
Output: true
```
**Example 2:**
```
Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
```
**Example 3:**
```
Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
```
**Follow up:**
Solve it without converting the integer to a string

O(n)思路：每次取第一位和最后一位的数字进行比较

不能转为string，而是直接对int进行操作
* 由Example知，负数必不是回文，并且只有一位数的整数必是回文
* 循环得到int数的数位，存于len中（如1234的数位为1000）
* 利用取整和取余来获得首尾数字（如 1221 这个数字，1221 / 1000， 则可得首位1， 1221 % 10， 可得末尾1），进行比较
  * 如果不相等，则不是回文，返回false
  * 若相等，则将整型数x去掉首尾再次检验，由于x去掉了首尾两位数，len也要减掉两个0，len=len/100

* 针对特例 如1000021 并没有影响<\br>
  第一次循环的时候，首尾各取出一个1，发现相等，之后取出中间的数字，为00002，也可以直接看成2<\br>
  此时取出首位置的时候，除以的是10000，所以取出的是0，尾位置取出的是2，二者不同，直接返回false。<\br>


```
class Solution {
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        if(x<10) return true;
        int len = 1;
        while(x/len >= 10)
            len = len*10;
        while(x!=0){
            if(x/len == x%10)
                x = (x - (x/len)*len)/10;
                // x = (x%len)/10;
            else
                return false;
            len = len/100;                       
        }
        return true;
    }
}
```
