# Regular Expression Matching

Given an input string (**s**) and a pattern (**p**), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.</br>
'*' Matches zero or more of the **preceding element**.

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

**Example 2:**
```
Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
```

**Example 3:**
```
Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
```
**Example 4:**
```
Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
```
**Example 5:**
```
Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
```



```

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        return self.helper(s,0,p,0,{})
    
    def helper(self, s, i, p, j, memo):
        if (i,j) in memo:
            return memo[(i,j)]

        # if string is empty, need to check if current pattern can match empty string
        if i==len(s):
            memo[(i,j)] = self.is_match_empty(p[j:])
            return memo[(i,j)]

        # if pattern is empty or start with *, then must be Flase
        if j==len(p) or p[j]=='*':
            return False
    
        if j+1<len(p) and p[j+1]=='*':
            # match 0 times or once
            res = self.helper(s,i,p,j+2,memo) or ((s[i]==p[j] or p[j]=='.') and self.helper(s,i+1,p,j,memo))
        else:
            res = (s[i]==p[j] or p[j]=='.') and self.helper(s,i+1,p,j+1,memo)
        
        memo[(i,j)] = res
        return res
    
    def is_match_empty(self, pattern):
        if len(pattern)%2 == 1:
            return False

        index = 0
        while index < len(pattern):
            if pattern[index] == '*' or pattern[index+1] != '*':
                return False
            index += 2
        return True
```




**DP思路: **

* dp[i][j] 表示 s中前i个字符组成的子串和p中前j个字符组成的子串是否能匹配。大小初始化为 (m+1) x (n+1)，加1的原因是要包含 dp[0][0] 的情况

* 通过分析写出递推式如下：
  * dp[0][0] = true，表示若s和p为空，匹配
  * dp[i][0] = false, i > 0, 表示若p为空，s非空，不匹配 (由于默认false，可省略此步)
  
  * 若p的当前字符p[j-1]不是'*'
    * s当前必须有值，且当前字符s[i-1] == p的当前字符p[j-1] 或p的当前字符p[j-1]=='.'，则dp[i][j] == dp[i-1][j-1]
    * 否则 dp[i][j] = false
    
  * 若p的当前字符p[j-1]是'*'
    * 情况1： 星号重复0次。 若dp[i][j-2]==true, 说明若'*'令前一个字符重复0次，则可令dp[i][j]匹配
    * 情况2： 星号重复至少一次。若s当前有值，且dp[i-1][j]==true，且当前字符s[i-1]与p的星号之前的字符p[j-2]相等 或 p[j-2]的等于'.'，说明若星号重复至少一次，则可令dp[i][j]匹配
    * 否则 dp[i][j] = false
* dp[m][n] 为最终结果

```
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        
        for(int i = 0; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(p.charAt(j-1) != '*'){
                    if(i>0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'))
                        dp[i][j] = dp[i-1][j-1];
                }
                else{
                    if(dp[i][j-2])
                        dp[i][j] = true;
                    else if(i > 0 && dp[i-1][j] && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'))
                        dp[i][j] = true;
                }
            }
        }
        return dp[m][n];
    }
}
```
</br>
</br>

**正常思路: 递归**

* 若p为空
  * 若s也为空，返回true，
  * 若s不为空，返回false

* 若p的长度为1
  * 若s长度也为1且与p相同，或是p为'.'，则返回true，
  * 若s长度不为1或长度为一但与p不同，且p不为'.'，返回false

* 判断p的第二个字符是否为'*'
  * 若p的第二个字符不为'*'
    * 若s为空，返回false，
    * 若s和p的首字符匹配，从各自的第二个字符开始调用递归函数匹配。
    * 若s和p首字符不匹配，返回false

  * 若p的第二个字符为'*'
    * 若s不为空，且s第一个字符与p第一个字符匹配
      * 返回调用递归函数匹配s和去掉前两个字符的p的结果（为了让'*'前面的字符出现正确的次数，因为s的头部可能有连续多个相同字符与p的首字符匹配），若匹配返回true，否则s去掉首字母（因为此时首字母匹配了，我们可以去掉s的首字母，而p由于星号的作用，可以有任意个首字母，所以不需要去掉），继续进行循环。
    * 若s为空或s第一个字符与p第一个字符不匹配，去掉p前两个字符，调用递归函数
```
比如s="ab", p="ab"，'a'=='a'进入while循环，"ab"和"b"不匹配，所以s变成"b"，跳出循环后，返回比较"b"和"b"，返回true
比如s="", p="a*"，由于s为空，不会进入任何的if和while，只能到最后的return比较，返回true
比如s="ab",p="c*a*b",'a'和'c'不匹配，不会进入循环，直接返回比较"ab"和"a*b",返回true
比如s="aa",p="a*",，'a'=='a'进入while循环,比较"aa"和"，不匹配，s变成"a",比较"a"和""，不匹配，s变成""，为空跳出循环，返回比较""和""，返回true
```
**注意:**
Java中String是对象类型，比较字符串相等不能用==，只能用equas()方法

```
class Solution {
    public static boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();

        
        if (p.length() == 1){
            if(s.length()==1 && (s.equals(p) || p.equals(".")))
                return true;
            else
                return false;
        }

        if (p.charAt(1) != '*') {
            if (s.length() == 0)
                return false;
            else if(s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
                return isMatch(s.substring(1), p.substring(1));
            else
                return false;
        }else{
            // next char is *
            while (s.length() > 0 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')) {
                if (isMatch(s, p.substring(2))) 
                    return true;
                s = s.substring(1);
            }
            return isMatch(s, p.substring(2));
        }
    }
}
```
