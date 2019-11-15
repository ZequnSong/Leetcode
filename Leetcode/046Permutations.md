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

**思路：排列问题 DFS**

引入visited 来记录每个数值是否被用过

* 若该数被用过，说明此时tmp里已存在，跳过
* 若没用过，将其加入tmp并标记为用过
* 递归，向下一层进行dfs遍历
* 当tmp.size() == nums.length，该分支结束，回溯重置tmp和visited中的值

```
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        boolean[] visited = new boolean[nums.length];
        dfs(visited, nums, new ArrayList<>(), res);
        return res;
    }
    
    private void dfs(boolean[] visited,
                     int[] nums,
                     List<Integer> tmp,
                     List<List<Integer>> res){
        if(tmp.size() == nums.length){
            res.add(new ArrayList<>(tmp));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
            tmp.add(nums[i]);
            dfs(visited, nums, tmp, res);
            tmp.remove(tmp.size()-1);
            visited[i] = false;
        }
    }
}
```



