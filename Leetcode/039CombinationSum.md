# Combination Sum

---
```
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, tmp, res);  
        return res;
    }
    
    void dfs(int[] candidates, int target, int current, List<Integer> tmp, List<List<Integer>> res){
        if(target < 0) return;
        if(target == 0){
            res.add(new ArrayList<>(tmp));
            return;
        }
            
        for(int i = current; i < candidates.length; i++){
            tmp.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, tmp, res);// not i + 1 because we can reuse same elements
            tmp.remove(tmp.size()-1);
        }
        
    }
}
```
