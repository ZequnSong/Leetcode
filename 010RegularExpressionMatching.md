# Regular Expression Matching

Given an input string (**s**) and a pattern (**p**), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the **entire** input string (not partial).

**Note:**

* s could be empty and contains only lowercase letters a-z.
* p could be empty and contains only lowercase letters a-z, and characters like . or *.

**Example 1:**
```
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
```

Example 2:
```
Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
```

Example 3:
```
Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
```
Example 4:
```
Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
```
Example 5:
```
Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
```

思路 递归

* 若p为空，若s也为空，返回true，反之返回false

* 若p的长度为1，s长度也为1且与p相同，或是p为'.'，则返回true，反之返回false

* 若p的第二个字符不为*，若此时s为空返回false，否则判断首字符是否匹配，且从各自的第二个字符开始调用递归函数匹配。

* 若p的第二个字符为*，进行下列循环，条件是若s不为空且首字符匹配（包括p[0]为点），调用递归函数匹配s和去掉前两个字符的p（这样做的原因是假设此时的星号的作用是让前面的字符出现0次，验证是否匹配），若匹配返回true，否则s去掉首字母（因为此时首字母匹配了，我们可以去掉s的首字母，而p由于星号的作用，可以有任意个首字母，所以不需要去掉），继续进行循环。

* 返回调用递归函数匹配s和去掉前两个字符的p的结果（这么做的原因是处理星号无法匹配的内容，比如s="ab", p="a*b"，直接进入while循环后，我们发现"ab"和"b"不匹配，所以s变成"b"，那么此时跳出循环后，就到最后的return来比较"b"和"b"了，返回true。再举个例子，比如s="", p="a*"，由于s为空，不会进入任何的if和while，只能到最后的return来比较了，返回true，正确）。

 
"ab"
".*c"


"aaa"
"aaaa"


class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for(int i = 0; i<p.length(); i++){
            if(p.charAt(i) == '*'&& dp[0][i-1]){
                dp[0][i+1] = true;
            }
        }
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < p.length(); j++){
                if(p.charAt(j) == s.charAt(i)){
                    dp[i+1][j+1] = dp[i][j];
                }
                if(p.charAt(j) == '.'){
                    dp[i+1][j+1] = dp[i][j]; 
                }
                if(p.charAt(j) == '*'){
                    if(p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) !='.'){
                        dp[i+1][j+1] = dp[i+1][j-1];                      
                    }else{
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1]||dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
