# Search in Rotated Sorted Array II

[Search in Rotated Sorted Array I](https://github.com/ZequnSong/Leetcode/blob/master/Leetcode/033SearchinRotatedSortedArray.md)

相同问题，只是nums可能包含重复数字

由于之前那道题不存在相同值，在比较中间值和最右值时就完全符合之前所说的规律：

* 如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的。

而如果可以有重复值，就会出现来面两种情况，[3 1 1] 和 [1 1 3 1]，对于这两种情况中间值等于最右值时，目标值3既可以在左边又可以在右边

* 对于这种情况其实处理非常简单，只要把最右值向左一位即可继续循环，如果还相同则继续移，直到移到不同值为止

```
class Solution {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return false;
        int left = 0, right = nums.length - 1;
        
        while(left + 1 < right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return true;
            else if(nums[mid] == nums[right]){
                while(right > mid && nums[mid] == nums[right])
                    right--;
            }else if(nums[mid] < nums[right]){
               if(nums[mid] < target && target <= nums[right])
                    left = mid;
                else
                    right = mid;
            }else{
               if(nums[mid] > target && target >= nums[left])
                    right = mid;
                else
                    left = mid;
            }
        }
        return nums[left] == target || nums[right] == target ;
    }
}
```
