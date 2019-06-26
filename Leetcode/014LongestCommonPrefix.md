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
* endPosi记录当前最长公前缀长度
* 不断遍历strs[]中的每一个String
  * 若访问到当前String没有第endPosi个字符，结束循环
  * 若当前String的第endPosi个字符与第一个字符的第endPosi个字符不匹配，结束循环
  * 若当前String是strs中最后一个String，当前最长公前缀长度+1，重置i=-1，从头开始新一轮遍历寻找下一个prefix字符
* 通过用substring方法返回共同前缀的子字符串
```
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        int endPosi = 0;
        for(int i = 0; i < strs.length; i++){
            if(endPosi >= strs[i].length() || strs[i].charAt(endPosi) != strs[0].charAt(endPosi) )
                break;    
            if(i == strs.length - 1){
                i = -1;
                endPosi++;
            }              
        }
        return strs[0].substring(0,endPosi);
    }
}
```
