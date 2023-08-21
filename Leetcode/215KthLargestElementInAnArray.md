# Kth Largest Element in an Array

Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?

**Example 1:**

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

**Example 2:**

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4




 




* 方法1 ： QuickSelect
```
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return helper(nums,0,nums.length-1,k);
    }

    private int helper(int[] nums, int left, int right, int k) {
        // larger than pivot, pivot, smaller than pivot        
        int partition = partitionIt(nums, left, right);
        if(partition == k-1) return nums[k-1];
        if(partition<k-1) return helper(nums,partition+1,right,k);
        else return helper(nums,left,partition-1,k);
    }

    private int partitionIt(int[] nums, int start, int end) {
        int left = start;
        int right = end;

        //int pivot = nums[(right-left)/2];
        int pivot = nums[left];
        while(left<right){
            //order matters here!
            //先减j，再加i，防止i停留在nums[i] < pivot的位置令swap(nums,left,i);出错
            while(left<right && nums[right] < pivot) right--;
            while(left<right && nums[left] >= pivot) left++;
            if(left==right)
              swap(nums,start,left);
            else
              swap(nums,right,left);
        }
        return left;
    }

    public void swap(int[] nums, int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```
