# Length of Last Word

Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

**Example:**
```
Input: "Hello World"
Output: 5
```

思路：

用cur记录当前单词的长度， 用last记录上一个单词的长度，这样做的目的是，万一字符串以多个空格结尾，cur就会为0，此时最后一个单词不在结尾，而在上一个非空格的位置

* 遍历字符串
  * 若当前字符不为空，给cur+1，继续遍历
  * 若当前字符为空
    * 若上一个字符不为空，则将cur(上一个单词长度)赋给last，将cur置为0
    * 若上一个字符为空，说明是连续空格，保证last和cur不变
* 最后返回cur，若cur为0，则返回last
```
class Solution {
    public int lengthOfLastWord(String s) {
        if(s.length() == 0) return 0;
        int last = 0, cur = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != ' ')
                cur++;
            else if(i > 0 && s.charAt(i-1) != ' '){
                last = cur;
                cur = 0;
            }
        }
        return cur == 0 ? last : cur;
    }
}
```

骚操作一：

由于只关心最后一个单词的长度，从字符串末尾开始，先将末尾的空格都去掉
然后从后往前开始找非空格的字符的长度即可

```
class Solution {
    public int lengthOfLastWord(String s) {
        int right = s.length() - 1, res = 0;
        while (right >= 0 && s.charAt(right) == ' ') --right;
        while (right >= 0 && s.charAt(right) != ' ' ) {
            --right; 
            ++res;
        }
        return res;
    }
}
```

骚操作二：

* trim()去掉字符串头尾的空白符
* lastIndexOf(" ")返回最后一个空格的下标，若无空格，返回-1

class Solution {
    public int lengthOfLastWord(String s) {
        return s.trim().length()-s.trim().lastIndexOf(" ")-1;
    }
}
