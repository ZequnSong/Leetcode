# Remove Duplicates from Sorted Array

Given a sorted array nums, remove the duplicates **in-place** such that each element appear only once and return the new length.

In-place means Do not allocate extra data structure, you must do this by **modifying the input array in-place** with O(1) extra memory.

**Example 1:**
```
Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
```

**Example 2:**
```
Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
```
**Clarification:**
Confused why the returned value is an integer but your answer is an array?
Note that the input array is passed in by **reference**, which means modification to the input array will be known to the caller as well.

Internally you can think of this:
```
// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```

**Method:**
* using to poiners: left & right
* initially both point to nums[0]
* if nums[left] == nums[right], move one step right pointer
* if nums[left] < nums[right], let nums[++left] == nums[right]
* when the right pointer iterates the entire array, the left + 1 is the length we want
```
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int left = 0, right = 0;
       while(right < nums.length){
           if(nums[left] < nums[right]){
               nums[++left] = nums[right];
           }
           right++;
        }
        return left+1;
        
    }
}
```
