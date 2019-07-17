# Wildcard Matching

Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
```
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
```
The matching should cover the entire input string (not partial).

**Example 1:**
```
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
```
**Example 2:**
```
Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
```
**Example 3:**
```
Input:
s = "acdcb"
p = "a*c?b"
Output: false
```

类似题目： [10. Regular Expression Matching](https://github.com/ZequnSong/Leetcode/blob/master/Leetcode/010RegularExpressionMatching.md)

**思路1：双指针O(n)**

最大的难点是对于星号的处理，星号有两种处理不了的问题：
1. 旦p中有s中不存在的字符，那么一定无法匹配，因为星号只能增加字符，不能消除字符，
2. 星号一旦确定了要匹配的字符串，对于星号位置后面的匹配情况也就鞭长莫及了。

所以p串中星号的位置很重要，用 jStar 来表示。

星号匹配到s串中的位置，使用 iStar 来表示iStar-1之前被星号匹配。

iStar 和 jStar 均初始化为 -1，表示默认情况下是没有星号的。

* 用两个变量i和j分别指向当前s串和p串中遍历到的位置。
* 开始进行匹配，若i小于s串的长度，进行 while 循环
  * 若当前两个字符相等，或着p中的字符是问号，则i和j分别加1
  * 若 p[j] 是星号，那么我们要记录星号的位置，jStar 赋为j，此时j再自增1，iStar 赋为i。
  * 若当前 p[j] 不是星号，并且不能跟 p[i] 匹配上，那么此时就要靠星号了，若之前星号没出现过，那么就return false

比如 s = "aa" 和 p = "c*"，此时 s[0] 和 p[0] 无法匹配，虽然 p[1] 是星号，但还是不行

如果星号之前出现过，可以续一波命，比如 s = "aa" 和 p = "*c"，当发现 s[1] 和 p[1] 无法匹配时，但是好在之前 p[0] 出现了星号，我们把 s[1] 交给 p[0] 的星号去匹配。至于如何知道之前有没有星号，这时就能看出 iStar 的作用了，因为其初始化为 -1，而遇到星号时，其就会被更新为i，那么我们只要检测 iStar 的值，就能知道是否可以使用星号续命

匹配完了s中的所有字符，但是之后我们还要检查p串，此时没匹配完的p串里只能剩星号，不能有其他的字符，将连续的星号过滤掉，如果j不等于p的长度，则返回false，
```
class Solution {
    public boolean isMatch(String s, String p) {
        if(p.isEmpty())
            return s.isEmpty();
        
        int i = 0, j = 0;
        int iStar = -1; // * match before s[iStar]
        int jStar = -1; // * index
        
        while(i < s.length()){
            if(j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')){
                i++;j++;
            }else if(j < p.length() && p.charAt(j) == '*'){
                iStar = i;
                jStar = j++;
            }else if(iStar >= 0){
                iStar++;
                i = iStar;
                j = jStar + 1;
            }else return false;
        }
        
        while(j < p.length() && p.charAt(j) == '*')
            j++;
        return j == p.length();
    }
}
```
