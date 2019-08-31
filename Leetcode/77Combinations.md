# Combinations

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

**Example:**
```
Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```

方法： dfs

* 注意与传统的排列组合不同， 此题[1,4]和[4,1]属于相同结果，不能重复输出
* 为避免此情况，新增一个start变量，每次dfs都从start的下一个位置开始，从而能避免大数字去组合前面的小数字而产生重复
```
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        dfs(n, k, res, tmp, 1);
        return res;
    }
    void dfs(int n, int k, List<List<Integer>> res, List<Integer> tmp, int start){
        if(tmp.size() == k){
            res.add(new ArrayList<>(tmp));
            return;
        }
        for(int i = start; i <= n; i++){
            tmp.add(i);
            dfs(n, k, res, tmp, i+1);
            tmp.remove(tmp.size()-1);
        }
    }
}
```
