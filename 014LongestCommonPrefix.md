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

思路1：
* StringBuilder res保存结果
* 不断遍历strs[]中的每一个String
  * 若访问到空String，结束算法，返回res
  * 若当前String是strs中第一个String，用tmp记录其第一个字符
  * 若当前String的第一个字符与tmp匹配，将该字符从String中去掉，继续访问下一个字符，否则结束算法，返回res
  * 若当前String是strs中最后一个String，且首字符匹配tmp，将tmp加入res中，重置i=0，从头开始新一轮遍历寻找下一个prefix字符
```
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        StringBuilder res = new StringBuilder("");
        char tmp = 'a';
        int i = 0;
        while(true){
            if(strs[i].length() == 0)
                break;
            if(i==0){
                tmp = strs[0].charAt(0);
            }
            
            if(strs[i].charAt(0) == tmp){
                strs[i] = strs[i].substring(1,strs[i].length());
                i++;
            }else
                break;
            if(i == strs.length){
                i=0;
                res.append(tmp);
            }          
        }
        return res.toString();
    }
}
```
