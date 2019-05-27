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

正常思路: 递归

* 若p为空
  * 若s也为空，返回true，
  * 若s不为空，返回false

* 若p的长度为1
  * 若s长度也为1且与p相同，或是p为'.'，则返回true，
  * 若s长度不为1或长度为一但与p不同，且p不为'.'，返回false

* 判断p的第二个字符是否为'*'
  * 若p的第二个字符不为*
    * 若s为空，返回false，
    * 若s和p的首字符匹配，从各自的第二个字符开始调用递归函数匹配。
    * 若s和p首字符不匹配，返回false

  * 若p的第二个字符为*
    * 若s不为空，且s第一个字符与p第一个字符匹配
      * 返回调用递归函数匹配s和去掉前两个字符的p的结果（为了让*前面的字符出现正确的次数，验证是否匹配），若匹配返回true，否则s去掉首字母（因为此时首字母匹配了，我们可以去掉s的首字母，而p由于星号的作用，可以有任意个首字母，所以不需要去掉），继续进行循环。
    * 若s为空或s第一个字符与p第一个字符不匹配，去掉p前两个字符，调用递归函数

* 返回调用递归函数匹配s和去掉前两个字符的p的结果（这么做的原因是处理星号无法匹配的内容

比如s="ab", p="a*b"，'a'=='a'进入while循环，"ab"和"b"不匹配，所以s变成"b"，跳出循环后，返回比较"b"和"b"，返回true
比如s="", p="a*"，由于s为空，不会进入任何的if和while，只能到最后的return比较，返回true
比如s="ab",p="c*a*b,'a'和'c'不匹配，不会进入循环，直接返回比较"ab"和"a*b",返回true

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
class Solution {
    public boolean isMatch(String s, String p) {
       
        if(s.length()==0 || p.length()==0) return false;
         int i = 0, j = 0;
        while(i<s.length()){
            if(j < p.length()-1 && p.charAt(j+1) =='*'){
                boolean res = false;
                if(j+2 == p.length())
                    return true; 
                while(i<s.length()){
                    res = res||isMatch(s.substring(i++,s.length()),p.substring(j+2,p.length()));
                    if(res == true)  return true;
                }
                return false; 

            }
            else if(j < p.length() && (p.charAt(j) =='.'|| p.charAt(j) == s.charAt(i))){
                i++;
                j++;
            }else
                return false;
        }
        if(j == p.length())    
            return true;
        else
            return false;
    }
}

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        boolean[][] f = new boolean[m + 1][n + 1];
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (i == 0 && j == 0) {
                    f[i][j] = true;
                    continue;
                }
                if (j == 0) {
                    continue;
                }
                if (pp[j - 1] != '*') {
                    f[i][j] = i > 0 && f[i - 1][j - 1] && (ss[i - 1] == pp[j - 1] || pp[j - 1] == '.');
                } else {
                    f[i][j] = (j > 1 && f[i][j - 2]) ||
                              (i > 0 && j > 1 && pp[j - 2] == ss[i - 1] && (f[i - 1][j])) ||
                              (i > 0 && j > 1 && pp[j - 2] == '.' && (f[i - 1][j]));
                }
            }
        }
        return f[m][n];
    }
}
