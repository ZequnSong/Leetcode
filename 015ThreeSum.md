# 3 Sum

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

**Note:**
The solution set must not contain duplicate triplets.

**Example:**
```
Given array nums = [-1, 0, 1, 2, -1, -4],
A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```
思路：

* 三个数且和为0，那么除了三个数全是0的情况之外，肯定会有负数和正数
* 要先fix一个数，然后去找另外两个数，找到两个数且和为第一个fix数的相反数
* 如果数组是有序的，那么可以用双指针以线性时间复杂度来遍历所有满足题意的两个数组合

* 对原数组进行排序，然后开始遍历排序后的数组，这里注意不是遍历到最后一个停止，而是到倒数第三个就可以了（保证后面至少有两个数，加上fix数能凑成三个数）
* 这里可以做剪枝优化，当遍历到正数的时候就break，因为数组现在是有序的，如果第一个要fix的数是正数，那么后面的数字就都是正数，永远不会出现和为0的情况
* 去重处理，从第二个数开始，如果和前面的数字相等，就跳过，确保相同的数字不会fix两次。左指针low = i + 1 而不是0，确保不同结果只出现一次
* 对于遍历到的数，用0减去这个fix的数得到一个target，然后只需要再之后找到两个数之和等于target即可。
* 用两个指针分别指向fix数字之后开始的数组首尾两个数，如果两个数和正好为target，则将这两个数和fix的数一起存入结果中
* 跳过重复数字，两个指针都需要检测重复数字。
* 两数之和小于target，则我们将左边那个指针i右移一位，使得指向的数字增大一些。同理，如果两数之和大于target，则我们将右边那个指针j左移一位，使得指向的数字减小一些

```
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++){
            if(nums[i]>0) break;
            int fix = nums[i];
            if(i>0  && nums[i] == nums[i-1])
                continue;
            int low = i + 1, high = nums.length - 1;
            while(low < high){
                if(nums[low] + nums[high] == -nums[i]){
                    res.add(Arrays.asList(nums[i],nums[low],nums[high]));
                    while(low < high && nums[low] == nums[low + 1]) low++;
                    while(low < high && nums[high] == nums[high - 1]) high--;
                    low++;
                    high--;
                }else if(nums[low] + nums[high] > -nums[i])
                    high--;
                else
                    low++;              
            }
        }
        return res;
    }
}
```
