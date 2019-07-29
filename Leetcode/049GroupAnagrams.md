# Group Anagrams

Given an array of strings, group anagrams together.

**Example:**
```
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```

**Note:**
* All inputs will be in lowercase.

思路：

* 把错位词的字符顺序重新排列，那么会得到相同的结果，所以重新排序是判断是否互为错位词的方法
* 由于错位词重新排序后都会得到相同的字符串，我们以此作为key，将互为错位词的词加入对应key所在的list中

```
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {       
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if(!map.containsKey(key))
                map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }        
        return new ArrayList<>(map.values());      
    }
}
```
