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

**思路：递归**

引入visited 来记录每个数值是否被用过

为了去重，首先将数组排序

* 若该数被用过，说明此时tmp里已存在，跳过
* 若没用过，但是该数与前一个数相同，且前一个数没被用过，跳过(因为前一个数没被用过，说明该数要么没被处理过，要么是递归结束后恢复状态时将visited值重置为0了，总之不管哪种情况，都意味着在当前layer该数值已经出现过，因为nums[i-1]==nums[i],要处理也是处理nums[i-1]，所以nums[i]直接跳过)
* 否则，将其加入tmp并标记为用过
* 递归，layer+1，表示向下一层进行dfs遍历
* 当该分支结束，回溯重置tmp和visited中的值

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
                if(visited[i]||(i>0 && nums[i] == nums[i-1] && visited[i-1]==false))
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
