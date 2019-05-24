# Two Sum

Given an array of integers, return **indices** of the two numbers such that they add up to a specific target.

You may assume that each input would have **exactly one** solution, and you may not use the same element twice.

-----------------------------
**Example:**
```
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
```

O(n)思路：
* 利用HashMap将之前遍历过的数存储起来，建立数字和其坐标位置之间的映射(求的是坐标，将坐标作为value，值作为key)
* 遍历数组，每访问一个新数，检查之前是否有遍历过的数与当前数相加等于target
  * 若map中存在value与current相加等于target，返回结果
  * 否则，将当前下标与值加入HashMap中

*注意：关于同一元素不能用两次：由于每次遍历是先检查是否有满足的解，如有则返回，如没有才向Map添加新数。所以不存在重复使用同一元素的问题，&& map.get(complement)!=i 可加可不加

```
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                res[0] = map.get(complement);
                res[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
```



