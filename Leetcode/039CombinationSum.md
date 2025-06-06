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
#PYTHON

class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        res = []
        comb = []
        total = 0
        candidates.sort()
        def dfs(candidates, target, res, comb, total, start):
            if total == target:
                res.append(comb[:])
                return
            
            for i in range(start, len(candidates)):
                total += candidates[i]
                if total <= target:
                    comb.append(candidates[i])
                    dfs(candidates, target, res, comb, total, i)
                    comb.pop()
                else:
                    return
                total -= candidates[i]
        
        dfs(candidates, target, res, comb, total, 0)
        return res
```







```
class Solution {
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

```
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, 0, new ArrayList<>(), target, res);
        return res;
    }

    //1. 定义: 从candidates数组中的startIndex开始挑选，放到combination中，且他们的和等于target
    void dfs(int[] candidates,//[1,2,3,4,5]
      int startIndex,
      List<Integer> comb,
      int target,
      List<List<Integer>> res) {
        //3. 出口
        if(target==0){
            res.add(new ArrayList<>(comb));
        }

        //2. 递归的拆解
        // 比如当前comb是[1], startIndex是1，该for循环要依次求
        // [1,2] , [1,3] , [1,4] ....为comb时的情况
        for(int i = startIndex; i < candidates.length; i++) {
            if(target-candidates[i]<0) continue;
            //[1] -> [1,2] 假设先把2加入comb
            comb.add(candidates[i]);
            // startIndex仍然为i因为可以重复取值
            dfs(candidates, i, comb, target-candidates[i],res);
            //[1,2]->[1]，dfs后要回溯，把‘2’remove掉，外侧for循环才能继续正确把‘3’加入comb
            comb.remove(comb.size()-1);
        }
    }
}
```
