# 4 Sum

Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? 

Find all unique quadruplets in the array which gives the sum of target.

**Note:**

The solution set must not contain duplicate quadruplets.

**Example:**

```
Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
```

思路：
* 排序
* 遍历fix第一个数i
  * 去重处理，如果和前面的数字相等，就跳过，确保相同的数字不会fix两次。左指针low = i + 1 而不是0，确保不同结果只出现一次
* 遍历fix第二个数j,从i+1开始,同样去重处理
* 用两个指针分别指向fix数字j之后开始的数组首尾两个数，如果四个数和正好为target，则将这四个数一起存入结果中
* 跳过重复数字，两个指针都需要检测重复数字。
* 若四数之和小于target，则将左边那个指针i右移一位，使得指向的数字增大一些。同理，如果两数之和大于target，则将右边那个指针j左移一位，使得指向的数字减小一些

```
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++){
            if(i > 0  && nums[i] == nums[i-1])
                continue;
            for(int j = i + 1; j < nums.length - 2; j++){
                if(j > i + 1  && nums[j] == nums[j-1])
                    continue;
                int low = j + 1, high = nums.length - 1;
                while(low < high){
                    int sum = nums[i] + nums[j] + nums[low] + nums[high];
                    if(sum == target){
                        res.add(Arrays.asList(nums[i],nums[j],nums[low],nums[high]));
                        while(low < high && nums[low] == nums[low + 1]) low++;
                        while(low < high && nums[high] == nums[high - 1]) high--;
                        low++;
                        high--;
                    }else if(sum > target)
                        high--;
                    else
                        low++;              
                }
            }
        }
        return res;    
    }
}
```
