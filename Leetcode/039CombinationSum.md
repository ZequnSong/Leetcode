# Combination Sum

Given a set of candidate numbers (candidates) **(without duplicates)** and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

**Note:**
All numbers (including target) will be positive integers.

The solution set must not contain duplicate combinations.

**Example 1:**
```
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
```
**Example 2:**
```
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```
---

**思路：**返回所有符合要求的解-->递归

题中明确给定数组中不含重复元素，因此不需预处理

* 创建变量res保存所有得到的解，tmp为每一个符合的解
* dfs递归函数，current记录当前递归到的下标，
  * 若target小于0，说明此路不通，剪掉
  * 若target等于0，说明找到解，此时的tmp就是一个解，将其加入到res中
  * 将当前数组的值加入tmp，每次调用递归，从target中减去当前数组的值，由于每个数值可以允许无限重复，所以递归时current是i而不是i+1
  * 当剪枝或找到一个解后，递归结束返回，要清空tmp中刚刚加入的数值

```
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        dfs(res, tmp, 0, target, candidates);
        
        return res;
    }
    
    void dfs(List<List<Integer>> res, List<Integer> tmp, int current, int target, int[] candidates){
        if(target < 0) return;
        if(target == 0){
            res.add(new ArrayList<>(tmp));
            return;
        }
        
        for(int i = current; i < candidates.length; i++){
            tmp.add(candidates[i]);
            dfs(res, tmp, i, target - candidates[i], candidates);
            tmp.remove(tmp.size()-1);
        }
    }
}
```
