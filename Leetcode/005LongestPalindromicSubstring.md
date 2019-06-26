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

O(n^2)思路:  以每一个字符为中心，向两边扩散来寻找回文串

* 定义两个变量 start 和 maxLength，分别表示最长回文子串的起点跟长度，分别用于返回最长回文子串和其长度
* 遍历s中字符
  * 首先判断剩余的字符数是否小于等于 maxLength 的一半</br>
    如果是的话，表明就算从当前到末尾到子串是半个回文串，那么整个回文串长度最多也就是 maxLen，既然 maxLen 无法再变长了，往后继续计算就没有意义，直接在当前位置break，否则继续

  * 处理奇偶情况：由于回文串的长度可奇可偶，比如 "bob" 是奇数形式的回文，"noon" 就是偶数形式的回文，两种形式的回文都要搜索</br>
    一般的，对于奇数形式的，从遍历到的位置为中心，向两边进行扩散；对于偶数情况，把当前位置和下一个位置当作偶数行回文的最中间两个字符，然后向两边进行搜索</br>
    这里可以用一种方法来同时处理奇偶的情况，用两个变量left和right分别指向当前位置，然后right先向右遍历跳过重复项，这个操作很必要，比如，对于 noon，i在第一个o的位置，如果以o为最中心往两边扩散，是无法得到长度为4的回文串的，只有先跳过重复，此时left指向第一个o，right指向第二个o，然后再向两边扩散。而对于 bob，i在第一个o的位置时，无法向右跳过重复，此时 left 和 right 同时指向o，再向两边扩散也是正确的，所以可以同时处理奇数和偶数的回文串，之后向两边进行搜索
  
* 更新 maxLen 和 start
* 返回最长回文子串

* 速度改进：i的增量从i++改为right+1</br>
  当回文序列中不含连续重复元素时，right+1 相当于i++</br>
  当回文序列中含连续重复元素时，i可直接跳到下一不重复元素的位置，例如 "abnooon",当i处于第一个o的位置，得到回文"nooon",当i处于第二和第三个o的位置，仍得到"nooon"一样的结果，所以可以直接跳过重复的元素


```
class Solution {
    public static String longestPalindrome(String s) {
        if(s.length() <= 1)
            return s;
        int start = 0, maxLength = 0;
        for(int i = 0; i < s.length();){
            if(s.length() - i - 1 <= maxLength/2)
                break;
            int left = i, right = i;
            while(right <  s.length() - 1 && s.charAt(right) == s.charAt(right+1))
                right++;
            i = right + 1;
            while(left > 0 && right < s.length() - 1 && s.charAt(left-1) == s.charAt(right+1)){
                left--;
                right++;
            }
            if(maxLength < right - left + 1){
                maxLength = right - left + 1;
                start = left;
            }    
        }
        return s.substring(start,start + maxLength);
        
    }
}
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
  * dp[i][j] 表示字符串区间 **[j, i]** 是否为回文串
  * 当 j = i 时，只有一个字符，肯定是回文串
  * 如果 i = j + 1，说明是相邻字符，此时需要判断 s[i] 是否等于 s[j]，若相等则是回文串
  * 如果i和j不相邻，即 i > j + 1 时，除了判断 s[i] 和 s[j] 相等之外，dp[i - 1][j + 1] 若为真，就是回文串
  * 通过分析写出递推式如下：
  ```
   dp[i][j] = 1                                           if i == j
            = (s[i] == s[j]) ? 1: 0                       if i == j + 1
            = (s[i] == s[j] && dp[i - 1][j + 1]) ? 1: 0   if i > j + 1
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
                if(i == j + 1 && (s.charAt(i) == s.charAt(j)))
                    dp[i][j] = 1;
                else if(i > j + 1 && (s.charAt(i) == s.charAt(j)) && dp[i - 1][j + 1] == 1 )
                    dp[i][j] = 1;
                else
                    dp[i][j] = 0;   
                //dp[i][j] = ((s.charAt(i) == s.charAt(j)) && ((i - j) < 2 || dp[i - 1][j + 1]==1)) ? 1 : 0;
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
