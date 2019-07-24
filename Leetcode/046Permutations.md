# Permutations

Given a collection of distinct integers, return all possible permutations.

**Example:**
```
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```
---

**思路：递归**

引入visited 来记录每个数值是否被用过

* 若该数被用过，说明此时tmp里已存在，跳过
* 若没用过，将其加入tmp并标记为用过
* 递归，layer+1，表示向下一层进行dfs遍历
* 当该分支结束，回溯重置tmp和visited中的值

```
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];     
        dfs(nums, 0, res, tmp, visited);
        return res;        
    }
    
    void dfs(int[] nums, int layer, List<List<Integer>> res, List<Integer> tmp, boolean[] visited){
        if(layer == nums.length){
            res.add(new ArrayList<>(tmp));
            return;
        }
        
        for(int i = 0; i < nums.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
            tmp.add(nums[i]);
            dfs(nums, layer+1, res, tmp, visited);
            tmp.remove(tmp.size() - 1);
            visited[i] = false;
        }
    }
}
```


