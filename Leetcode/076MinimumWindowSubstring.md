# Minimum Window Substring

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

**Example:**
```
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
```

**Note:**

* If there is no such window in S that covers all characters in T, return the empty string "".
* If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

思路：
遍历一遍数组，用hashmap记录t中字母出现的次数，用滑动窗口来寻找最小子串

* hashmap记录t中字母出现的次数
* left，right为滑动窗口左右指针，count意思是t中的字母有多少个出现在滑动窗口中，minLeft和minLen记录当前最小子串的起始位置和长度
* 右指针right右移遍历数组，若当前字母在t中
  * 则将map中该字母对应的出现次数减1，若减1后次数仍为非负数，说明当前遍历到的字母有效，是t串中的字母，给count加1
  * 若count等于t串长度，说明当前窗口属于满足要求的子串，尝试收缩左边界，即left+1,在收缩之前先
    * 更新当前最小子串的起始位置和长度
    * 若左指针元素包含在t串中，将map中该字母对应的出现次数加1，若加1后次数为正数，则说明此时少了t串中一个字母，当前子串不满足要求了，给count减1
* 最后返回minLeft对应子串
```
class Solution {
    public String minWindow(String s, String t) {
        
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            if(!map.containsKey(t.charAt(i)))
                map.put(t.charAt(i), 1);
            else
                map.put(t.charAt(i),map.get(t.charAt(i))+1);
        }
        
        int left = 0;
        int count = 0;
        int minLeft = 0;
        int minLen = Integer.MAX_VALUE;
  
        for(int right = 0; right < s.length(); right++){
            char cha = s.charAt(right);
            if(map.containsKey(cha)){
                map.put(cha,map.get(cha)-1);
                if(map.get(cha) >= 0)
                    count++;
                while(count == t.length()){
                    if(right - left + 1 < minLen){
                        minLen = right - left + 1;
                        minLeft = left;
                    }
                    if(map.containsKey(s.charAt(left))){
                        map.put(s.charAt(left),map.get(s.charAt(left))+1);
                        if(map.get(s.charAt(left))>0)
                            count--;
                    }
                    left++;
                }
            }
        }
        if(minLen == Integer.MAX_VALUE) return "";
        return s.substring(minLeft, minLeft+minLen);
    }
}
```
