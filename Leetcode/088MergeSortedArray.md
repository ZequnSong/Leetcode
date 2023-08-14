# Merge Sorted Array

https://leetcode.com/problems/merge-sorted-array/

Merge Sort

正常Merge Sort从小到大，每次都有可能数组整体挪动。。
现在nums1的后面有空位置，只要你能想到要 倒过来从大到小插入数组就好了

```
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1, j = n-1;
        int cur = m + n - 1;
        while( i >=0 && j >= 0 ){
            if(nums1[i] > nums2[j]){
                nums1[cur--] = nums1[i--];
            }
            else{
                nums1[cur--] = nums2[j--];
            }
        }
        while(j >= 0){
            nums1[cur--] = nums2[j--];
        }
    }
}
```
