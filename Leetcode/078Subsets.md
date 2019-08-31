# Subsets

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

**Example:**

```
Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```
方法： dfs

找到所有子集，元素先后顺序不影响结果

<img src="/pictures/question_78.png" width="150">

* 每次调用dfs先直接往res里添加tmp结果
* 每次调用dfs，循环时i从start开始

```
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        dfs(nums, res, tmp, 0);
        return res;
    }
    void dfs(int[] nums, List<List<Integer>> res, List<Integer> tmp, int start){
        res.add(new ArrayList<>(tmp));
        for(int i = start; i < nums.length; i++){
            tmp.add(nums[i]);
            dfs(nums, res, tmp, i+1);
            tmp.remove(tmp.size()-1);
        }
    }
}
```
