# Longest Substring Without Repeating Characters

Given a string, find the length of the **longest substring** without repeating characters.

**Example 1:**
```
**Input:** "abcabcbb"
**Output:** 3 
**Explanation:** The answer is "abc", with the length of 3. 
```
**Example 2:**
```
**Input:** "bbbbb"
**Output:** 1
**Explanation:** The answer is "b", with the length of 1.
```
**Example 3:**
```
**Input:** "pwwkew"
**Output:** 3
**Explanation:** The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
```
             

O(n)思路：滑动窗口

窗口内的都是没有重复的字符，尽可能的扩大窗口的大小
用HashMap记录不同字符最后出现的位置
为了求出窗口的大小，我们需要建立左右边界: length = right - left + 1
右边界就是当前遍历到的字符的位置,左边界是当前不重复子序列的起点
 
* 遍历字符串s
  * 如果当前字符之前出现过，分两种情况：
    * 如果之前的字符在滑动窗口内，即字符下标大于left，需要在滑动窗口内去掉这个已经出现的字符。去掉的方法并不需要将左边界left一位一位向右遍历查找，由于HashMap已经保存了该重复字符最后出现的位置，所以直接移动left指针到该重复字符的next位置
    * 如果不在滑动窗口内，则没事，更新map中该字符的最新位置，不必单独列出，可与下一步合并
  * 如果字符没出现过，向map中添加该字符的位置信息
  * 每次用出现过的窗口大小来更新结果res = Max(res, i - j + 1)
 
```
class Solution {
    public int lengthOfLongestSubstring(String s) {      
        Map<Character,Integer> map = new HashMap<>();
        int res = 0;
        int right, left = 0;
        for(right = 0; right < s.length(); right++){
            if(map.containsKey(s.charAt(right))){
                if(map.get(s.charAt(right))>=left)
                    left = map.get(s.charAt(right)) + 1;
            }
            map.put(s.charAt(right), right);
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
```
