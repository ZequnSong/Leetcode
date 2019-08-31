# Remove Duplicates from Sorted Array II

Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

**Example:**
```
Given nums = [0,0,1,1,1,1,2,3,3],

return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
It doesn't matter what values are set beyond the returned length.
```

思路：

此题要求in-place，空间复杂度为O(1)

* 变量count： 录每个数字出现了几次，
* 变量idx：只要有数字重复了超过两次，由于数组升序排列，所以从重复的数字开始往后所有的位置都需要in-place替换，idx指向第一个重复的数字的下标

* 遍历一次解决
* 若当前字符和之前相同，则给count加1
  * 此时若第一次出现重复超过两次，则idx指向当前的下标（即第三个多的数字的下标）
* 若当前字符和之前不同，则说明是出现了新数字，将count重置为1，代表该数字出现了一次
* 最后，若idx不为0，且当前数字没有重复超过两次，说明则当前数字需要移位，移至idx下标位置，之后idx右移一位
* 否则idx等于0意味着nums目前为止一直符合规定，无需任何操作
* 最后若idx等于0，因为全部合法，返回整个nums长度，否则返回idx值作为合法字串的长度  

```
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length < 3) return nums.length;
        int idx = 0, count = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i-1]){
                if(++count == 3 && idx == 0)
                    idx = i;
            }
            else count = 1;
            if(count <=2 && idx != 0)
                nums[idx++] = nums[i];
        }
        return idx == 0 ? nums.length : idx;
    }
}
```
