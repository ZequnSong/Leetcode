# Intersection of Two Arrays

Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.

 
**Example 1:**

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

**Example 2:**

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.



* 方法1 ： HashMap

 ```
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for(int i = 0; i < nums1.length; i++){
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                intersect.add(nums2[i]);
            }
        }
        int[] result = new int[intersect.size()];
        int i = 0;
        for (Integer num : intersect) {
            result[i++] = num;
        }
        return result;
    }
}
```
* 方法2 ： Merge two sorted array， 排好序merge的时候找出intersection
```
  class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = 0;
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        while(i<nums1.length && j<nums2.length) {
            if(nums1[i] < nums2[j]) {
                i++;
            }
            else if(nums2[j] < nums1[i]) {
                j++;
            }
            else {
                res[k++] = nums1[i];
                i++;
                j++;
                while(i<nums1.length && nums1[i] == nums1[i-1]){i++;}
                while(j<nums2.length && nums2[j] == nums2[j-1]){j++;}
            } 
        }
        return Arrays.copyOf(res, k);
    }
}
```
* 方法3: Binary Search 排序一个数组，遍历另一个数组，二分搜索每个元素intersection
