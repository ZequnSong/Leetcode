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
* mid = left + (right - left) / 2 向下取整中值
* 若nums[mid]等于target，直接找到，返回
* 若nums[mid]大于target，右边界左移至mid - 1, 小于target，左边界右移至 mid + 1;
* 当最后left=right
  * 若此点小于target，应在此点的下一个位置插入，此时left=mid+1=left+1 即返回left即可
  * 若此点大于target，应在此点插入，此时left没变，则返回left即可

```
class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            
            if(nums[mid] == target) return mid;
            if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }
}
```
