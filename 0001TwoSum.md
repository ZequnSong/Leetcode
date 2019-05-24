# Two Sum

Given an array of integers, return **indices** of the two numbers such that they add up to a specific target.

You may assume that each input would have **exactly one** solution, and you may not use the same element twice.

**Example :**
> Given nums = [2, 7, 11, 15], target = 9,

> Because nums[0] + nums[1] = 2 + 7 = 9,

> return [0, 1].


线性的时间复杂度
只遍历一遍数组，并且每访问一个新数，要检查之前是否有遍历过的数与其相加等于target
所以利用HashMap将之前遍历过的数存储起来，建立数字和其坐标位置之间的映射
注意
关于同一元素不能用两次：由于每次遍历是先检查是否有 满足的解，如有则返回，如没有才向Map添加新数
所以不存在重复使用同一元素的问题，&& map.get(complement)!=i 可加可不加
注意要判断查找到的数字不是第一个数字，比如target是4，遍历到了一个2，那么另外一个2不能是之前那个2，

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



