# Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

**Example 1:**
```
Input: haystack = "hello", needle = "ll"
Output: 2
```
**Example 2:**
```
Input: haystack = "aaaaa", needle = "bba"
Output: -1
```
**Clarification:**
What should we return when needle is an empty string? This is a great question to ask during an interview.
For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

**Solution:**
在一个字符串(haystack)中找另一个字符串(needle)第一次出现的位置
* 如果needle为空，则返回0
* 如果haystack为空,或needle长度大于haystack长度，则返回-1
* 然后开始遍历haystack字符串
  * 我们并不需要遍历整个母字符串，而是遍历到剩下的长度和子字符串相等(m - i = n)的位置即可，这样可以提高运算效率
  * 对于每一个字符，都要遍历一遍needle子字符串，一个一个字符的对应比较
    * 如果对应位置有不等的，则跳出循环
    * 如果一直都没有跳出循环，则说明子字符串出现了，则返回起始位置即可
```
class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0) return 0;
        int m = haystack.length(), n = needle.length();
        if(m == 0 || m < n) return -1;

        for(int i = 0; i <= m - n ; i++){
            int j = 0;
            for(j = 0; j < n; j++)
                if(haystack.charAt(i + j) != needle.charAt(j))
                    break;
            if(j == n)
                return i;           
        }
        return -1;
        
    }
}
```
