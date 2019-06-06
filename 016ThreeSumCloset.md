# 3Sum Closest
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. 
You may assume that each input would have exactly one solution.

**Example:**
```
Given array nums = [-1, 2, 1, -4], and target = 1.
The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
```

思路：与3Sum十分类似，时间复杂度O(n^2)

* 初始化res，注意为了防止res-target溢出，res不能简单的取int最大值
* 排序数组
* 遍历数组，遍历到到倒数第三个停止（保证后面至少有两个数，加上fix数能凑成三个数）
  * 先fix遍历到的数，然后用左右指针去找另外两个数，比较三数和sum与target的差值
    * 若当前差值小于res与target差值，更新res为sum值
    * 若sum大于target，说明应该调小一些，故右指针左移
    * 若sum小于等于target，说明应该调大一些，故左指针右移
* 由于题目说明只有一个解，说明数组内无重复元素，不需去重


```
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int res = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++){
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(Math.abs(sum - target) < Math.abs(res - target))
                    res = sum;
                if(sum > target)
                    right--;
                else
                    left++;
            }
        }
        return res;     
    }
}
```
