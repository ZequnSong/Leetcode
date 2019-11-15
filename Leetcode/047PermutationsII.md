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
* 若没用过，但是该数与前一个数相同，且前一个数没被用过，跳过(因为前一个数没被用过，要么是递归结束后恢复状态时将visited值重置为0了，要么是该数也被跳过了，总之不管哪种情况，因为nums[i-1]==nums[i],要处理也是处理之前的nums[i-1]，所以nums[i]直接跳过)
* 否则，将其加入tmp并标记为用过
* 递归，layer+1，表示向下一层进行dfs遍历
* 当该分支结束，回溯重置tmp和visited中的值

```
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
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
            if(i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            visited[i] = true;
            tmp.add(nums[i]);
            dfs(visited, nums, tmp, res);
            tmp.remove(tmp.size()-1);
            visited[i] = false;
        }
    }
}
```
