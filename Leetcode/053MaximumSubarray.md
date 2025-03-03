# Maximum Subarray

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

**Example:**
```
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
```

思路1：O(n)

* 定义两个变量res和curSum，其中res保存最终要返回的结果，即最大的子数组之和，curSum初始值为0
* 每遍历一个数字num，比较curSum + num和num中的较大值存入curSum，然后再把res和curSum中的较大值存入res
* 以此类推直到遍历完整个数组，可得到最大子数组的值存在res中

```
class Solution {
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int curSum = 0;
        
        for(int i = 0; i < nums.length; i++){
            curSum = Math.max(curSum+nums[i], nums[i]);
            res = Math.max(curSum, res);
        }
        return res;
    }
}
```

* 复杂一点的做法，消耗O(n)的空间创建一个sum数组 sum[i] = nums[0]+nums[1]+...+nums[i-1]
* 那数组nums中任意两点之和 nums(i ~ j) = sum[j+1]-sum[i]   可知我们要令sum[j+1]尽可能大 令sum[i]尽可能小

```
//sum[i] = nums[0]+nums[1]+...+nums[i-1]
//sum of nums(i ~ j) = sum[j+1]-sum[i]
int[] sum = new int[nums.length];
        int sumMin = 10000;
        int res = -10000;
        for(int i = 0; i < nums.length; i++){
            sum[i] = i == 0 ? nums[i] : (sum[i-1]+nums[i]);
            int curMax = Math.max(sum[i],sum[i] - sumMin);
            res = Math.max(res,curMax);
            sumMin = Math.min(sumMin, sum[i]);
        }
        return res;
```
* 优化-》 不需要构造数组
```
class Solution {
    public int maxSubArray(int[] nums) {
        // sum[i] = nums[0]+nums[1]+...+nums[i-1]
        // 则子数组i-j的和 sum[i--j] = sum[j+1]-sum[i];
        // largest sum相当于我们需要找
        // -> sum[j+1] 最大
        // -> sum[i] 最小

        int sum = 0, minSum = 0;
        int curMax = nums[0];
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i]; // -> sum[j+1]
            //以当前点j为右边界的所有子数组中，最大和为当前sum减去目前得到的最小sum[i]->minSum
            //则sum-minSum等于以当前点j为右边界的所有子数组中的largest sum
            //Math.max(curMax, sum-minSum)相当于和之前的j-1，j-2....为右边界时的所有子数组
            // 的largest sum比较，保留目前的largest sum
            curMax = Math.max(curMax, sum-minSum);
            minSum = Math.min(minSum, sum); //保证sum[i]永远最小
        }
        return curMax;        
    }
}
```



思路2：Divide and Conquer

* 把数组一分为二
* 若中间的数不在最大子数组内，分别找出左边和右边的最大子数组之和,
* 若中间的数在最大子数组内，从中间开始向左右分别扫描
* 求出的最大值分别和左右两边得出的最大值相比较取最大的那一个


```
public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        return helper(nums, 0, nums.length - 1);
    }
    public int helper(int[] nums, int left, int right) {
        if (left >= right) return nums[left];
        int mid = left + (right - left) / 2;
        int lmax = helper(nums, left, mid - 1);
        int rmax = helper(nums, mid + 1, right);
        int mmax = nums[mid], t = mmax;
        for (int i = mid - 1; i >= left; --i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }
        t = mmax;
        for (int i = mid + 1; i <= right; ++i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }
        return Math.max(mmax, Math.max(lmax, rmax));
    }
}
```


