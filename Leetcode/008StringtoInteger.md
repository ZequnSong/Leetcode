# String to Integer (atoi)

Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

**Note:**

* Only the space character ' ' is considered as whitespace character.
* Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1]. If the numerical value is out of the range of representable values, INT_MAX (2^31 − 1) or INT_MIN (−2^31) is returned.

**Example 1:**
```
Input: "42"
Output: 42
```
**Example 2:**
```
Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.
```
**Example 3:**
```
Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
```
**Example 4:**
```
Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical 
             digit or a +/- sign. Therefore no valid conversion could be performed.
```
**Example 5:**
```
Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−2^31) is returned.
```

O(n)思路：

* 若字符串开头是空格，则跳过所有空格，到第一个非空格字符，如果没有，则返回0.
* 若第一个非空格字符是符号 +/-，则标记 sign 的正负
* 若下一个字符不是数字，则返回0. 不考虑小数点和自然数的情况
* 如果下一个字符是数字，则转为整形存下来，若接下来再有非数字出现，则返回目前的结果。
* 溢出问题，如果超过了整型数的范围，则用边界值替代当前值

```
class Solution {
    public int myAtoi(String str) {
        int res = 0;
        boolean neg = false;
        int i = 0;
        while(i < str.length() && str.charAt(i) == ' ')
            i++;
        if(i < str.length() && (str.charAt(i) == '-' || str.charAt(i) == '+'))
            neg = str.charAt(i++) == '-' ? true : false;
        
        while(i < str.length() && str.charAt(i)>='0' && str.charAt(i) <= '9'){
            if(res > Integer.MAX_VALUE/10)
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            if(res == Integer.MAX_VALUE/10){
                if(!neg && str.charAt(i) - '0' > 7)
                    return Integer.MAX_VALUE;
                if(neg && str.charAt(i) - '0' > 8)
                    return Integer.MIN_VALUE;
            }
            res = res*10 + str.charAt(i++) - '0';
        }
        return neg ? -res : res;
        
    }
}
```
