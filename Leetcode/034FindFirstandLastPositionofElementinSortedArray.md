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
        if(nums == null || nums.length == 0) return res;
        int left = 0, right = nums.length-1;
        while(left+1<right){
            int mid = left + (right-left)/2;
            if(nums[mid]<target)
                left = mid;
            else
                right = mid;                
        }
        if(nums[left] == target)
            res[0] = left;
        else if(nums[right] == target)
            res[0] = right;
        else
            return res;
        left = 0;
        right = nums.length-1;
        while(left+1<right){
            int mid = left + (right-left)/2;
            if(nums[mid]<=target)
                left = mid;
            else
                right = mid;
        }
        if(nums[right] == target)
            res[1] = right;
        else
            res[1] = left;
        return res;
    }
}
        
```
        
