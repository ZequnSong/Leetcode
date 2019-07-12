# Trapping Rain Water

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
