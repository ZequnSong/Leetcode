# Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:
```
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```
思路：对于列出所有结果的题,首先考虑用递归Recursion

DFS:
* 定义两个变量left和right分别表示剩余左右括号的个数
* 递归，每次递归分为两支，一支在当前字符串后加"("，一支在当前字符串后加")"
  * 如果在某次递归时，剩余左括号的个数大于剩余右括号的个数，说明此时生成的字符串中右括号的个数大于左括号的个数，会出现非法串不匹配，对这种情况进行剪枝，直接返回，不继续处理。
  * 如果left和right都为0，则说明此时生成的字符串已有n个左括号和n个右括号，且字符串合法，则存入结果中后返回。
  * 如果以上两种情况都不满足，若此时left大于0，则调用递归函数，注意参数的更新，若right大于0，则调用递归函数，同样要更新参数。

```
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        dfs(n,n,"",res);
        return res;        
    }
    void dfs(int left, int right, String out, List<String> res){
        if(left < 0 || right < 0 || left > right) return;
        if(left == 0 && right == 0){
            res.add(out);
            return;
        }
        
        dfs(left-1,right,out+"(",res);
        dfs(left,right-1,out+")",res);
    }
}
```
