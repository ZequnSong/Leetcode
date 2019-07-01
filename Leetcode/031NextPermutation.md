# Next Permutation

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
```
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
```
---
求下一个排列顺序，如果给定数组是降序，则说明是全排列的最后一种情况，则下一个排列就是最初始情况.
如果给定数组是下面一个例子:
1　　2　　7　　4　　3　　1
则下一个排列为：
1　　3　　1　　2　　4　　7
如何得到？我们通过观察原数组可以发现，如果从末尾往前看，数字逐渐变大，到了2时才减小的，
然后我们再从后往前找第一个比2大的数字，是3，那么我们交换2和3，再把此时3后面的所有数字转置一下即可

**方法：发现规律很重要**
* 先从后往前找数值突然减小的位置m，若找不到则说明是倒序，直接排序返回
* 再从后往前找第一个比nums[m]大的值,与nums[m]交换，再将m+1开始往后的数组排序返回
* 

```
class Solution {
    public void nextPermutation(int[] nums) {
        int m=-1,i;
        for(i = nums.length-1; i >= 1 ; i--){
            if(nums[i]>nums[i-1]){
                m = i-1;
                break;
            }
        }
        if(m<0){
            Arrays.sort(nums);
            return;
        }else{
            for(i = nums.length-1; i >= 0 ; i--){
                if(nums[i]>nums[m]){
                    int temp = nums[m];
                    nums[m] = nums[i];
                    nums[i] = temp;
                    break;
                }
            }            
        }
        Arrays.sort(nums,m+1,nums.length);
    }
}
```
