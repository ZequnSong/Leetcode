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

```
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        
        Arrays.sort(candidates);
        dfs(res, tmp, 0, target, candidates);
        return res;        
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> tmp, int current, int target, int[] candidates){
        if(target < 0) return;
        if(target == 0){
            res.add(new ArrayList<>(tmp));
            return;
        }
        
        for(int i = current; i < candidates.length; i++){
            if(i+1 < candidates.length && candidates[i] == candidates[i+1]){
                int count = 1;
                int j = i;
                tmp.add(candidates[j]);
                while(j + 1 < candidates.length && candidates[j] == candidates[j+1]){
                    tmp.add(candidates[j++]);
                    count++;
                }
                dfs(res, tmp, j+1, target - candidates[i]*count, candidates);
                while(count>0){
                    tmp.remove(tmp.size() - 1);
                    count--;
                }
                    
            }
            else{
                tmp.add(candidates[i]);          
                dfs(res, tmp, i+1, target - candidates[i], candidates);
                tmp.remove(tmp.size() - 1);           
            }
        }
    }
}
```
