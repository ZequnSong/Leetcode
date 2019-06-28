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

getOrDefault(Object key, V defaultValue)
It returns the value to which the specified key is mapped, or defaultValue if the map contains no mapping for the key.

---

**Method:**

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
