# Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

![A mapping of digit to letters](/pictures/question_17.png)

**Example:**
```
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```

**Note:**
Although the above answer is in lexicographical order, your answer could be in any order you want.

思路：

* 建立String数组存储每个数字对应的一串字母，为了使数字和下标对应，数组0和1位置插入空字符串
* 遍历输入数字
  * 取出res链表中所有长度小于等于i的结果
  * 当前遍历到的数字对应的每一个字母，都加入到取出的结果的末尾，再添加回链表中
  
  
```
class Solution {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> res = new LinkedList<>();
        if(digits.isEmpty()) return res;
        String[] dict = new String[] {"","","abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");
        for(int i = 0; i < digits.length(); i++){
            int digi = Character.getNumericValue(digits.charAt(i));
            while(res.peek().length() <= i){
                String t = res.remove();
                for(char s : dict[digi].toCharArray())
                    res.add(t+s);
            }
        }
        return res;
    }
}
```


