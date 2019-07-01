# Substring with Concatenation of All Words

You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

**Example 1:**
```
Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
```

**Example 2:**
```
Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []
```
---

**Method:**
* 建立哈希表，存words表中的单词，value为每个单词出现的次数
* 由于words中**单词长度相等**，可以一个词一个词的遍历
* 由于要找到所有的子串，外层遍历从第一个单词开始，到string总长减去子串应有长度的位置结束
  * 每次循环创建一个哈希表，初始等于之前建立的表，这样每次访问一个单词我们都能从中减去一次该单词出现的次数，且不对原始表修改
  * 内层循环从当前位置，到当前位置加子串应有长度的位置结束，每次前进一个word长度，按词遍历
  * 若该单词在哈希表中存在，并且value大于等于1，将该value减1，继续遍历，否则说明当前位置不会有目标子串，跳出当前内循环
  * 当内循环遍历到最后，若计数counter等于words长度，说明该子串符合要求，加入res
  



getOrDefault(Object key, V defaultValue)
It returns the value to which the specified key is mapped, or defaultValue if the map contains no mapping for the key.

```
 class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if(s.length() == 0 || words.length == 0 || words[0].length()==0) return res;
   
        int wordLen = words[0].length();
        int strLen = words.length*wordLen;    
        Map<String, Integer> warehouse = new HashMap<>();
      
        for(String str : words)
            warehouse.put(str,warehouse.getOrDefault(str,0) + 1);
        
        for(int i = 0; i <= s.length()-strLen; i++){//iterate through all windows
            Map<String, Integer> set = new HashMap<>(warehouse);
            int counter = words.length;
            for(int j = i; j < i + strLen; j+=wordLen){
                //for each window, check if each word is in HashMap
                String subs = s.substring(j,j+wordLen);
                if(set.containsKey(subs)){
                    if(set.get(subs)> 0){
                        set.put(subs,set.get(subs)-1);
                        counter--;
                    }
                    else
                        break;
                }
                else
                    break;
            }
            if(counter == 0)
                res.add(i);
        }
        return res;
    }
}
```
