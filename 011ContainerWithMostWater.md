# Container With Most Water

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

**Note:** You may not slant the container and n is at least 2

![The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.](/pictures/question_11.jpg)





O(n^2)思路：




```
class Solution {
    public int maxArea(int[] height) {
        int volum = 0;

        for(int i = 0; i < height.length - 1; i++){
            for(int j = i + 1; j < height.length; j++){
                int tmp = Math.min(height[i],height[j])*(j-i);
                if(tmp>volum){
                    volum = tmp;
                }
            }
        }
        return volum;
        
    }
}
```



 
 

class Solution {
    public int maxArea(int[] height) {
        int res = 0;
        int l = 0;
        int r = height.length - 1;
        while(l < r){
            res = Math.max(res, Math.min(height[l],height[r])*(r - l));
            if(height[l] < height[r])
                l++;
            else
                r--;            
        }
        return res;
    }
}
