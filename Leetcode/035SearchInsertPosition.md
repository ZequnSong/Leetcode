# Search Insert Position

Given a sorted array and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

**Example 1:**
```
Input: [1,3,5,6], 5
Output: 2
```

**Example 2:**
```
Input: [1,3,5,6], 2
Output: 1
```

**Example 3:**
```
Input: [1,3,5,6], 7
Output: 4
```

**Example 4:**
```
Input: [1,3,5,6], 0
Output: 0
```

---

二分法
* 若找到，返回对应index
* 若没找到，分情况找到对应插入下标

```
class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        while(left + 1 < right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] < target)
                left = mid;
            else
                right = mid;
        }
        if(nums[left] == target)
            return left;
        else if(nums[right] == target)
            return right;
        
        if(target < nums[left])
            return left;
        else if(target < nums[right])
            return right;
        else return right+1;
    }
}
```
