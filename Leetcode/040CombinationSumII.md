# Combination Sum II

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may **only be used once** in the combination.

**Note:**

All numbers (including target) will be positive integers.
The solution set must **not contain duplicate** combinations.

**Example 1:**
```
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```

**Example 2:**
```
Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
```

**思路：**
类似 [39. Combination Sum](https://github.com/ZequnSong/Leetcode/blob/master/Leetcode/039CombinationSum.md)

不同之处：1). 给定数组中每个数最多只能用一次 2). 给定数组中可能含有重复元素

* 给数组排序，便于去重
* 递归函数for循环中，若当前数值与之前数值相等，则continue跳过
  * 例如： 2，2，2，4
  * 当前startIndex处于第一个2时，会探索所有包含2的可能组合：(2),(2,2),(2,2,2),(2,2,2,4),(2,2,4),(2,4)
  * 所以为了去掉重复，当遍历startIndex之后的数值时，需要跳过和之前相等的数值，因此后面的两个2会直接跳过
* 由于每个数最多只能用一次，递归调用时startIndex=i+1 而不是i，意味着当前数只取一次就跳到下一个数

``` 
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length == 0) return res;
        
        Arrays.sort(candidates);
        dfs(0, target, candidates, new ArrayList<>(), res);
        return res;
    }
    
    private void dfs(int startIndex,
                     int remainTarget,
                     int[] candidates,
                     List<Integer> tmp,
                     List<List<Integer>> res){
        if(remainTarget < 0) return;
        if(remainTarget == 0){
            res.add(new ArrayList<>(tmp));
            return;
        }
        
        for(int i = startIndex; i < candidates.length; i++){
            if(i > startIndex && candidates[i] == candidates[i-1]) continue;
            tmp.add(candidates[i]);
            dfs(i+1, remainTarget - candidates[i], candidates, tmp, res);
            tmp.remove(tmp.size()-1);
        }
    }
}
```
