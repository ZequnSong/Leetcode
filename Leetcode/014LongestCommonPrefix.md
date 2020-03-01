# Longest Common Prefix

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

**Example 1:**
```
Input: ["flower","flow","flight"]
Output: "fl"
```
**Example 2:**
```
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```
**Note:**

All given inputs are in lowercase letters a-z.

思路：
* s记录strs[]中第一个string，作为当前最长公前缀
* 不断遍历strs[]中的剩下的每一个String
  * 若访问到当前String为空，结束循环返回空
  * 若当前String的第j个字符与当前公前缀的第j个字符不匹配，缩短公前缀为substring(0,j)
  * 若当前String与当前公缀全部匹配，但当前公缀还有多余字符，将当前String作为当前公缀
```
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        String s = strs[0];
        for(int i = 1; i < strs.length; i++){
            if(strs[i].equals("")) return "";
            for(int j = 0; j < strs[i].length(); j++){                
                if(j < s.length() && s.charAt(j) != strs[i].charAt(j)){
                    s = s.substring(0,j);
                    break;
                }
                if(j == strs[i].length()-1 && s.length()>=strs[i].length())
                    s = strs[i];
            }
        }
        
        return s;
        
    }
}

```
