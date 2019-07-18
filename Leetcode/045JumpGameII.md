# Jump Game II

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

**Example:**
```
Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
```
**Note:**
You can assume that you can always reach the last index.

```

**思路**

* 若数组长度为1，则已处于终态，直接返回0
* 循环遍历数组
  * 若当前nums[i]能一步直接达到终点，则res+1并返回
  * 遍历当前nums[i]所能达到的范围内所有数值
    * 找到这些数值能达到的最远距离max，和能到达该距离的对应的数值下标cur
    * 如果max大于等于nums边界，说明此时可以达到终点，nums[i]到达nums[cur]，nums[cur]到达终点，步数为2，则res+2返回
  * 令i等于cur，即从nums[i]走一步到达其范围内能jump最远距离的点nums[cur]，res加1，继续遍历数组nums
  
  ```
  class Solution {
    public int jump(int[] nums) {
        int res = 0;
        if(nums.length == 1)
            return 0;
        for(int i = 0; i < nums.length; ){
            int max = 0; 
            int cur = 0;
            if(nums[i] + i >= nums.length - 1)
                return ++res;
            for(int j = i + 1; j <= nums[i]+i; j++){
                if(j + nums[j] > max){
                    max = j + nums[j];
                    cur = j;                    
                    if(max >= nums.length-1)
                        return res+2;
                }          
            }          
            i = cur;
            res++;
        }
        return res;
    }
}
```
