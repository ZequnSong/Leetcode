# Search in Rotated Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

**Example 1:**
```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```
**Example 2:**
```
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```
---

对于数组 [0 1 2 4 5 6 7] 共有下列七种旋转方法（红色表示中点之前或者之后一定为有序的）：

<img src="/pictures/question_33.png" width="200">

二分搜索法的关键在于获得了中间数后，判断下面要搜索左半段还是右半段,观察上面红色的数字都是升序的，由此可以观察出规律:

如果中间的数小于**最右边的数**，则右半段是有序的

若中间数大于**最右边数**，则左半段是有序的

只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边
```
class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while(left + 1 < right){
            int mid = left + (right - left)/2;
            if(nums[mid]==target) return mid;
            if(nums[mid] < nums[right]){
                if(nums[mid] < target && nums[right] >= target)
                    left = mid;
                else
                    right = mid;
            }else{
                if(nums[mid] > target && nums[left] <= target)
                    right = mid;
                else
                    left = mid;
            }
        }
        if(nums[right] == target) return right;
        if(nums[left] == target) return left;
        return -1;
    }
}
```
