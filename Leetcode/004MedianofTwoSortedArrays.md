# Median of Two Sorted Arrays

There are two sorted arrays **nums1** and **nums2** of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be **O(log (m+n))**.

You may assume **nums1** and **nums2** cannot be both empty.

**Example 1:**
```
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
```
**Example 2:**
```
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
```
**Solution 1:**

O(log(n+m))思路：在两个有序数组中找到第k大的元素

* 假设两个有序数组的长度分别为 **m** 和 **n** ，由于数组长度之和 **m+n** 奇偶不确定，需要分情况讨论：
  * 若为奇数，直返回最中间的数，即第 (m+n)/2+1 个元素
  * 若为偶数，求最中间两个数的平均值，即第(m+n)/2和第(m+n)/2+1元素的平均值
  
* 定义一个函数来在两个有序数组中找到第K个元素
  * 为了避免拷贝产生新的数组从而增加时间复杂度，使用两个变量i和j分别来标记数组 nums1 和 nums2 的起始位置。
  * 使用二分法，对K二分，即分别在 nums1 和 nums2 中查找第 K/2 个元素
  * 当某一个数组的起始位置大于等于其数组长度时，说明其所有数字均已被淘汰，相当于在另一个数组中找数字，可直接找出。
  * 若K=1，即求第一大的元素值，只当两数组均只剩一个元素时发生，只要比较 nums1 和 nums2 的起始位置i和j上的值，取小的。
  
  一般的情况：
  
  * 由于两数组的长度不定，可能某个数组没有第 K/2 个数字，所以需要先检查数组中到底存不存在第 K/2 个数字。
    * 如果存在，即i + k/2 -1 < nums1.length, 就取出该下标对应的值
    * 如果不存在，即i + k/2 -1 >= nums1.length，就赋值上一个整型最大值
      * 为什么要赋整型最大值？当一个数组特别大，另一个数组特别小时，我们需要扔掉大数组的前k/2个数，由于小数组不存在第 K/2 个数字，令其无穷大会保证在下面的逻辑中我们会扔掉大数组的前k/2个数。或者我们可以把小数组看成一个很长的数组，只不过后面多出来的元素每一个都是无穷大


    * 如果某个数组不存在第 K/2 个数字，那么就淘汰另一个数组的前 K/2 个数字。例如 nums1 = {3}，nums2 = {2, 4, 5, 6, 7}，K=4，要找两个数组混合中第4个数字，那么分别在 nums1 和 nums2 中找第2个数字，发现 nums1 中只有一个数字，不存在第二个数字，那么 nums2 中的前2个数字可以直接跳过，因为不管 nums1 中的那个数字是大是小，第4个数字绝不会出现在 nums2 的前两个数字中。
  
  * 比较这两个数组的第 K/2 小的数字 mid1 和 mid2 的大小
    * 如果第一个数组的第 K/2 个数字小的话，那么说明我们要找的数字肯定不在 nums1 中的前 K/2 个数中，所以我们可以将其淘汰，将 nums1 的起始位置向后移动 K/2 个，并且此时的K也自减去 K/2，调用递归
      * 为什么去掉nums1中的前 K/2 个数安全？ 这里可以用反证法：假设我们要找的第k个数在nums1的前 K/2 个数中，当我们按照大小依次从nums1和nums2中一共取出k-1个数的时候，由于nums1的第k/2个数<nums2的第k/2个数, 此时nums1中的前k/2个数一定全都被取出，并且nums2的第k/2个数还没有被取出，由于此时只取出了k-1个数，不可能存在第k个数，和第k个数在nums1的前 K/2 个数中相矛盾。
      例如 nums1 = {1, 3}，nums2 = {2, 4, 5}，K=4，要找两个数组混合中第4个数字，那么分别在 nums1 和 nums2 中找第2个数字，nums1 中的第2个数字是3，nums2 中的第2个数字是4，由于3小于4，所以混合数组中第4个数字肯定不在 nums1的前K/2个元素中，所以我们可以将 nums1 的起始位置向后移动 K/2 个。    
    * 反之，我们淘汰 nums2 中的前 K/2 个数字，并将 nums2 的起始位置向后移动 K/2 个，并且此时的K也自减去 K/2，调用递归即可。
   
```
    比如  ArrayA: 1 2 4 6 9 10    k=6
         ArrayB:  3 5 7 8
    则 A的第k/2个数是4， B的第k/2个数是7，第6个数一定在4（较小的k/2）的后半段或者7（较大的k/2）的前半段
```
    
```
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if((m+n)%2==0){
            return (findKth(nums1, 0, nums2, 0, (m+n)/2) + findKth(nums1, 0, nums2, 0, (m+n)/2+1))/2.0;
        } else {
            return findKth(nums1, 0, nums2, 0, (m+n)/2+1);
        }
    }

    private int findKth(int[] nums1, int index1, int[] nums2, int index2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        //当起始index超出该数组最大index时说明该数组已经全部找完，余下的kth直接从另一个数组return即可
        if(index1 > m-1) return nums2[index2 + k-1];
        if(index2 > n-1) return nums1[index1 + k-1];
        //当k==1时，直接比较即可
        if(k==1) return Math.min(nums1[index1],nums2[index2]);

        int mid1 = (index1 + k/2 -1 > m-1) ? Integer.MAX_VALUE : nums1[index1+k/2-1];
        int mid2 = (index2 + k/2 -1 > n-1) ? Integer.MAX_VALUE : nums2[index2+k/2-1];
        if(mid1 > mid2){
            //若mid1大，则抛弃nums2中k/2个数，因此还剩下k-k/2个数要找 （奇偶性同样考虑在内）
            return findKth(nums1, index1, nums2, index2+k/2, k-k/2);
        } else {
            //若mid2大，则抛弃nums1中k/2个数，因此还剩下k-k/2个数要找 （奇偶性同样考虑在内）
            return findKth(nums1, index1+k/2, nums2, index2, k-k/2);
        }
    }
}
```
**Solution 2:**

O(n+m)思路：合并排序merge两个有序数组nums1和nums2，直接求中值

```
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merge = merge(nums1, nums2);
        int length = merge.length;
        double res;
        if(length%2 == 0)
            res = (merge[length/2-1]+ merge[length/2])/2.;
        else
            res = merge[length/2];
        return res;
    }
    
    int[] merge(int[] a, int[] b){
        int[] res = new int[a.length + b.length];
        int indexa = 0, indexb = 0;
        while(indexa != a.length && indexb != b.length){
            if(a[indexa]>b[indexb]){
                res[indexa+indexb] = b[indexb++];
            }else{
                res[indexa+indexb] = a[indexa++];
            }
        }
        
        if(indexa != a.length){
            System.arraycopy(a,indexa,res,indexa+indexb,a.length - indexa);
        }
        if(indexb != b.length){
            System.arraycopy(b,indexb,res,indexa+indexb,b.length - indexb);
        }
        return res;
        
    }
}
```
