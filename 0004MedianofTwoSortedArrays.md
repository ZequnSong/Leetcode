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
O(log(n+m))思路：

* 假设两个有序数组的长度分别为m和n，由于两个数组长度之和 m+n 的奇偶不确定，因此需要分情况来讨论。对于奇数的情况，直接找到最中间的数即可，偶数的话需要求最中间两个数的平均值

* 定义一个函数来在两个有序数组中找到第K个元素
  * 首先，为了避免拷贝产生新的数组从而增加时间复杂度，我们使用两个变量i和j分别来标记数组 nums1 和 nums2 的起始位置。
  * 使用二分法，对K二分，即分别在 nums1 和 nums2 中查找第 K/2 个元素
  
然后来处理一些 corner cases，比如当某一个数组的起始位置大于等于其数组长度时，说明其所有数字均已经被淘汰了，相当于一个空数组了，那么实际上就变成了在另一个数组中找数字，直接就可以找出来了。还有就是如果 K=1 的话，那么我们只要比较 nums1 和 nums2 的起始位置i和j上的数字就可以了。难点就在于一般的情况怎么处理？因为我们需要在两个有序数组中找到第K个元素，为了加快搜索的速度，我们要使用二分法，那么对谁二分呢，数组么？其实要对K二分，意思是我们需要分别在 nums1 和 nums2 中查找第 K/2 个元素，

注意由于两个数组的长度不定，所以有可能某个数组没有第 K/2 个数字，所以需要先检查数组中到底存不存在第 K/2 个数字，如果存在就取出来，否则就赋值上一个整型最大值。
如果某个数组没有第 K/2 个数字，那么我们就淘汰另一个数组的前 K/2 个数字即可。举个例子来说吧，比如 nums1 = {3}，nums2 = {2, 4, 5, 6, 7}，K=4，我们要找两个数组混合中第4个数字，那么我们分别在 nums1 和 nums2 中找第2个数字，我们发现 nums1 中只有一个数字，不存在第二个数字，那么 nums2 中的前2个数字可以直接跳过，为啥呢，因为我们要求整个混合数组的第4个数字，不管 nums1 中的那个数字是大是小，第4个数字绝不会出现在 nums2 的前两个数字中，所以可以直接跳过。

最后就是二分法的核心啦，比较这两个数组的第 K/2 小的数字 midVal1 和 midVal2 的大小，如果第一个数组的第 K/2 个数字小的话，那么说明我们要找的数字肯定不在 nums1 中的前 K/2 个数字，所以我们可以将其淘汰，将 nums1 的起始位置向后移动 K/2 个，并且此时的K也自减去 K/2，调用递归，举个例子来说吧，比如 nums1 = {1, 3}，nums2 = {2, 4, 5}，K=4，我们要找两个数组混合中第4个数字，那么我们分别在 nums1 和 nums2 中找第2个数字，nums1 中的第2个数字是3，nums2 中的第2个数字是4，由于3小于4，所以混合数组中第4个数字肯定在 nums2 中，所以我们可以将 nums1 的起始位置向后移动 K/2 个。反之，我们淘汰 nums2 中的前 K/2 个数字，并将 nums2 的起始位置向后移动 K/2 个，并且此时的K也自减去 K/2，调用递归即可，参见代码如下：

```
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if((m+n)%2==0)
            return (findKth(nums1,0,nums2,0,(m+n)/2)+findKth(nums1,0,nums2,0,(m+n)/2+1))/2.0;
        else
            return findKth(nums1,0,nums2,0,(m+n)/2+1);            
    }
    
    int findKth(int[] nums1, int i, int[] nums2, int j, int k){
        if(i >= nums1.length) return nums2[j + k - 1];
        if(j >= nums2.length) return nums1[i + k - 1];
        if(k == 1) return Math.min(nums1[i],nums2[j]);
        
        int mid1 = (i + k/2 - 1 < nums1.length) ? nums1[i+k/2-1] : Integer.MAX_VALUE;
        int mid2 = (j + k/2 - 1 < nums2.length) ? nums2[j+k/2-1] : Integer.MAX_VALUE;
        if(mid1 < mid2)
            return findKth(nums1,i+k/2,nums2,j,k-k/2);
        else
            return findKth(nums1,i,nums2,j+k/2,k-k/2);
    }

}
```

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
