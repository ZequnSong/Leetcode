# Find First and Last Position of Element in Sorted Array

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

**Example 1:**
```
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
```
**Example 2:**
```
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
```
---

使用两次二分查找法，第一次找到左边界，第二次调用找到右边界
```
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1,-1};        
        if(nums.length == 0) return res;
        int left = 0;
        int right = nums.length - 1;
        
        //find left boundary
        while(left < right){
            //找左边界，mid取下平均值
            int mid = left + (right - left) / 2;
            if(nums[mid] < target)
                left = mid + 1; //不会漏掉左边界
            else if(nums[mid] > target)
                right = mid - 1; //不会漏掉左边界
            else
                right = mid;//此时nums[mid] = target,mid有是左边界的可能，由于是求左边界，向左收缩，故缩小right = mid 而不是mid+1
        }
        // 若不包含target，right可能会取到-1，故先测试right是否小于0
        if(right < 0 || nums[right] != target) return res;
        res[0] = right;
        right = nums.length - 1;
        
        //find right boundary
        while(left < right){
            //找右边界，mid取上平均值
            int mid = left + (right - left+1) / 2;
            if(nums[mid] > target)
                right = mid - 1; //不会漏掉右边界
            else if(nums[mid] < target)
                left = mid + 1; //不会漏掉右边界
            else 
                left = mid;//此时nums[mid] = target, mid有是右边界的可能，由于是求右边界，向右收缩，故缩小left = mid 而不是mid+1
        }
        res[1] = left;
        return res;
    }
}
        
```
        
