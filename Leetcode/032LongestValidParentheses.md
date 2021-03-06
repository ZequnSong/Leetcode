# Longest Valid Parentheses

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
**Example 1:**
``
Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
``
**Example 2:**
```
Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
```

**Method 1 : Stack**
求最长有效括号长度

* 定义left变量来记录合法括号串的起始位置
* 遍历字符串
  * 如果遇到左括号，则将**当前下标**压入栈(很明显入栈的只能是左括号的下标)
  * 如果遇到右括号
    * 如果当前栈为空，说明该右括号非法，则将合法坐标起始位置记录到下一位置，即i+1
    * 如果栈不为空，则将栈顶元素取出，此时
      * 若栈为空，则更新结果和i - left + 1中的较大值(起始位置一直到当前位置i都是合法坐标，如0--3为合法串，则长度为4=3-0+1)
      * 如果栈不为空，更新结果和i - 栈顶元素peek中的较大值(此时栈顶元素为左侧最近的多余左括号的下标，则此时合法位置相当于peek+1，如0--4，0为栈顶元素，i为4，长度为4=4-(0+1)+1)

```
class Solution {
    public int longestValidParentheses(String s) {
        int res = 0;
        int left = 0;
        Stack<Integer> p = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i)=='('){
                p.push(i);
            }else{
                if(p.isEmpty()){
                    left = i + 1;
                }else{
                    p.pop();
                    if(p.isEmpty()){
                        res = Math.max(res,i - left + 1);
                    }else{
                        res = Math.max(res,i - p.peek());
                    }
                }
            }
            
        }
        return res;
    }
}
```
**Method 2 : DP**
求极值问题一般想到DP或Greedy

1. Notation: DP[i] = 以s[i]为结尾的longest valid parentheses substring的长度。
2. Recurrence:
  * if s[i] = '(', DP[i] = 0. Because any string end with '(' cannot be a valid one
  * if s[i] = ')'
    * if s[i-1] = '(', DP[i] = DP[i-2] + 2
    * if s[i-1] = ')' and s[i-DP[i-1]-1] = '(', DP[i] = DP[i-1] + 2 + DP[i-DP[i-1]-2]
    * i-DP[i-1]-1 意思是找到匹配s[i-1]的'('的位置再往前一个，若是'('，则与s[i]匹配，整个子串有效
    
For example, input “()(())”, at i = 5, DP array is [0,2,0,0,2,0], longest[5] = longest[4] + 2 + longest[1] = 6.


```
class Solution {
    public int longestValidParentheses(String s) {
        if(s.length() <= 1) return 0;
        int[] dp = new int[s.length()];
        int result = 0;
        int leftCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else if (leftCount > 0){
                dp[i] = dp[i - 1] + 2;
                dp[i] += (i - dp[i]) >= 0 ? dp[i - dp[i]] : 0;
                result = Math.max(result, dp[i]);
                leftCount--;
            }
        }
        return result;
    }
}
```
