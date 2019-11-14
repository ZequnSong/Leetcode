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

**思路：** 返回所有符合要求的解--> DFS

题中明确给定数组中不含重复元素，因此不需预处理

* dfs递归
  * 定义：从candidates数组中的startIndex开始挑选，放到combination中，且他们的和等于target

  * startIndex记录当前递归到的下标，
  * 若target小于0，说明此路不通，剪掉
  * 若target等于0，说明找到解，此时的combination就是一个解，将其加入到res中
  * 将当前数组的值加入combination，每次调用递归，从target中减去当前数组的值，由于每个数值可以允许无限重复，所以递归时startIndex是i而不是i+1
  * 当剪枝或找到一个解后，递归结束返回，要清空combination中刚刚加入的数值

```
class Solution {
    boolean findOne = false;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length == 0) return res;
        dfs(candidates, 0, target, new ArrayList<>(), res);
        return res;        
    }
    
    private void dfs(int[] candidates, 
                     int startIndex, 
                     int target,
                     List<Integer> combination,
                     List<List<Integer>> res){
        if(target < 0) return;
        if(target == 0){
            res.add(new ArrayList<>(combination));
            findOne = true;
            return;
        }
        
        for(int i = startIndex; i < candidates.length; i++){
            combination.add(candidates[i]);
            dfs(candidates, i, target - candidates[i], combination, res);
            combination.remove(combination.size()-1);
        }
    }
}
```
