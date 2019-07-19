# Permutations II

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

**Example:**
```
Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```

与 [Permutationa](https://github.com/ZequnSong/Leetcode/blob/master/Leetcode/046Permutations.md) 十分类似

```
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];       
        dfs(nums,0,res,tmp,visited);        
        return res;        
    }
    void dfs(int[] nums, int layer, List<List<Integer>> res, List<Integer> tmp, boolean[] visited){
            if(layer == nums.length){
                res.add(new ArrayList<>(tmp));
                return;
            }            
            for(int i = 0; i < nums.length; i++){
                if(visited[i]||(i>0 && nums[i] == nums[i-1] && !visited[i-1]))
                    continue;                
                tmp.add(nums[i]);
                visited[i] = true;              
                dfs(nums, layer+1, res, tmp, visited);                
                tmp.remove(tmp.size()-1);
                visited[i] = false;
            }
            
        }
}
```
