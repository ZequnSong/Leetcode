# Longest Palindromic Substring

Given a string **s**, find the longest palindromic substring in **s**. 

You may assume that the maximum length of **s** is 1000.

----------------------

**Example 1:**
```
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
```
**Example 2:**
```
Input: "cbbd"
Output: "bb"
```
**Solution 1:**

O(n^2)思路:
传统的验证回文串的方法就是两个两个的对称验证是否相等，那么对于找回文字串的问题，就要以每一个字符为中心，像两边扩散来寻找回文串
要注意奇偶情况，由于回文串的长度可奇可偶，比如 "bob" 是奇数形式的回文，"noon" 就是偶数形式的回文，两种形式的回文都要搜索
对于奇数形式的，我们就从遍历到的位置为中心，向两边进行扩散
对于偶数情况，我们就把当前位置和下一个位置当作偶数行回文的最中间两个字符，然后向两边进行搜索

也可以不使用子函数，直接在一个函数中搞定，我们还是要定义两个变量 start 和 maxLen，分别表示最长回文子串的起点跟长度，在遍历s中的字符的时候，我们首先判断剩余的字符数是否小于等于 maxLen 的一半，是的话表明就算从当前到末尾到子串是半个回文串，那么整个回文串长度最多也就是 maxLen，既然 maxLen 无法再变长了，计算这些就没有意义，直接在当前位置 break 掉就行了。否则就要继续判断，我们用两个变量left和right分别指向当前位置，然后我们先要做的是向右遍历跳过重复项，这个操作很必要，比如对于 noon，i在第一个o的位置，如果我们以o为最中心往两边扩散，是无法得到长度为4的回文串的，只有先跳过重复，此时left指向第一个o，right指向第二个o，然后再向两边扩散。而对于 bob，i在第一个o的位置时，无法向右跳过重复，此时 left 和 right 同时指向o，再向两边扩散也是正确的，所以可以同时处理奇数和偶数的回文串，之后的操作就是更新 maxLen 和 start 了


```
class Solution {
public:
    string longestPalindrome(string s) {
        if (s.size() < 2) return s;
        int n = s.size(), maxLen = 0, start = 0;
        for (int i = 0; i < n;) {
            if (n - i <= maxLen / 2) break;
            int left = i, right = i;
            while (right < n - 1 && s[right + 1] == s[right]) ++right;
            i = right + 1;
            while (right < n - 1 && left > 0 && s[right + 1] == s[left - 1]) {
                ++right; --left;
            }
            if (maxLen < right - left + 1) {
                maxLen = right - left + 1;
                start = left;
            }
        }
        return s.substr(start, maxLen);
    }
};
```
**Solution 2:**

O(n^2)思路：Dynamic Programming 

| i\j | 0 | 1 | 2 | 3 | ... | n |
| --- | - | - | - | - | --- | - |
| 0 | 1 | - | - | - | - | - |
| 1 | ? | 1 | - | - | - | - |
| 2 | ? | ? | 1 | - | - | - |
| 3 | ? | ? | ? | 1 | - | - |
| ... | ? | ? | ? | ? | 1 | - |
| n | ? | ? | ? | ? | ? | 1 |

* 维护一个二维数组 dp
  * dp[i][j] 表示字符串区间 [i, j] 是否为回文串
  * 当 i = j 时，只有一个字符，肯定是回文串
  * 如果 i + 1 = j，说明是相邻字符，此时需要判断 s[i] 是否等于 s[j]，若相等则是回文串
  * 如果i和j不相邻，即 j - i >= 2 时，除了判断 s[i] 和 s[j] 相等之外，dp[i + 1][j - 1] 若为真，就是回文串
  * 通过分析写出递推式如下：
  ```
    dp[i][j] = 1                                     if i == j
             = (s[i] == s[j])                        if j = i + 1
             = (s[i] == s[j] && dp[i + 1][j - 1])    if j > i + 1
  ```

    
```    
class Solution {
    public static String longestPalindrome(String s) {

        if (s.isEmpty()) return "";
        int left = 0, right = 0, len = 0;
        int[][] dp = new  int[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++)
            dp[i][i] = 1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                dp[i][j] = ((s.charAt(i) == s.charAt(j)) && ((i - j) < 2 || dp[i - 1][j + 1]==1)) ? 1 : 0;
                if (dp[i][j]==1 && len < i - j + 1) {
                    len = i - j + 1;
                    left = j;
                    right = i;
                }
            }
        }
        return s.substring(left, right + 1);
    }
}
```
