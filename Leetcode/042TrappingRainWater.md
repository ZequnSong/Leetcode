# Trapping Rain Water

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

![watertrap](/pictures/question_42.png)

**Example:**
```
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
```

**O(n)思路：**
左右指针：类似[Container With Most Water](https://github.com/ZequnSong/Leetcode/blob/master/Leetcode/011ContainerWithMostWater.md)

* 循环至左右指针相遇
* 找到两端数值中较小的一边，设其为min
  * 若left为较小一边，向右移left，直到当前高度大于等于min为止，每次移动都会接住min-height[i]的水  
  * 若right为较小一边，向左移right，直到当前高度大于等于min为止，每次移动都会接住min-height[r]的水  

```
public class Solution {
    public int trap(int[] height) {
        int res = 0;
        int l = 0, r = height.length - 1;
        
        while(l < r){
            int min = Math.min(height[l], height[r]);
            if(height[l] == min){
                l++;
                while( l < r && height[l] < min)
                    res += min - height[l++];
            }else{
                r--;
                while( l < r && height[r] < min)
                    res += min - height[r--];
            }
        }
        return res;
    }
}
```

**O(n^2)思路：Brute Force**

* 对于数组中每个元素，分别计算其能够装水的量
* 计算方法为得到每个元素左右的最大高度中的最小值min，若该元素高度小于min，则装水量为min-height[i]

```
public class Solution {
    public int trap(int[] height) {
        int res = 0;
        int max_l = 0, max_r = 0;
        
        for(int i = 1; i < height.length - 1; i++){
            max_l = Math.max(max_l, height[i-1]);
            for(int j = height.length - 1; j > i; j--){
                max_r = Math.max(max_r, height[j]);
            }
            int min = Math.min(max_l,max_r);
            if(height[i] < min)
                res += min - height[i];
            max_r = 0;
        }
        return res;
    }
}
```
