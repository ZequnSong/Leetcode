# Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.

Note that an empty string is also considered valid.

**Example 1:**
```
Input: "()"
Output: true
```
**Example 2:**
```
Input: "()[]{}"
Output: true
```
**Example 3:**
```
Input: "(]"
Output: false
```
**Example 4:**
```
Input: "([)]"
Output: false
```
**Example 5:**
```
Input: "{[]}"
Output: true
```

思路：用栈

* 遍历字符串
* 如果当前字符为左半边括号时，则将其压入栈中
* 如果遇到右半边括号时
  * 若此时栈为空，则直接返回false
  * 如不为空，则取出栈顶元素
    * 若为对应的左半边括号，则继续循环，反之返回false

```
class Solution {
    public boolean isValid(String s) {
        if(s.isEmpty())
            return true;
        Stack<Character> parentheses = new Stack<>();
        int i = 0;
        while(i < s.length()){
            switch (s.charAt(i)){
                case '(':
                case '{':  
                case '[': parentheses.push(s.charAt(i)); break;
                case ')': if(parentheses.isEmpty() || parentheses.pop()!= '(')
                              return false; break;
                case '}': if(parentheses.isEmpty() || parentheses.pop()!= '{')
                              return false; break;
                case ']': if(parentheses.isEmpty() || parentheses.pop()!= '[')
                              return false; break;                 
            }
            i++;
        }
        if(parentheses.isEmpty())
            return true;
        else
            return false;
    }
}
```
