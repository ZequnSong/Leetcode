# Sort Colors

Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

**Example:**
```
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
```

方法一：two-pass 

* 遍历两遍数组
* 第一次记录每种颜色出现的次数
* 第二次根据次数进行修改

```
class Solution {
    public void sortColors(int[] nums) {
        int j = 0, k = 0, l = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i]==0)
                j++;
            if(nums[i]==1)
                k++;
            if(nums[i]==2)
                l++;
        }
        for(int i = 0; i < nums.length; i++){
            if(i<j)
                nums[i] = 0;
            else if(i < j + k)
                nums[i] = 1;
            else
                nums[i] = 2;
        }          
    }
}
```

方法二： one pass 遍历一遍数组

