# Jump Game

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

**Example 1:**
```
Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
```

**Example 2:**
```
Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
```

思路:
* 为了知道能否到达末尾，维护一个变量reach，表示最远能到达的位置，初始化为0
* 遍历数组中每一个数字
  * 如果当前坐标大于reach, 说明此时已经不能再走了，因为此处已经unreachable
  * 或者reach已经抵达最后一个位置则跳出循环
  * 否则就更新reach的值为其和i + nums[i]中的较大值，其中i + nums[i]表示当前位置能到达的最大位置
```
class Solution {
    public boolean canJump(int[] nums) {
        int reach = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > reach || reach >= nums.length -1) break;     
            reach = Math.max(reach, i + nums[i]);
        }
        return reach >= nums.length-1;
    }
}
```

DP思路:

* 维护一个一位数组dp，其中dp[i]表示处于i位置时还能够前进的最大步数
* 状态转移方程：dp[i] = max(dp[i - 1]-1, nums[i])
  * 当前位置的剩余步数（dp值）由上一个位置能够前进的最大步数减1(因为需要花一个跳力到达当前位置)和当前位置的跳力中的较大那个数决定
  * 如果当上一个位置能够前进的最大步数,即dp数组的值不是正数，说明无法抵达当前位置，则直接返回false，最后循环结束后直接返回true即可

```
class Solution:
    def canJump(self, nums: List[int]) -> bool:
        n = len(nums)
        # dp[i] max jump capability from i
        dp = [0 for _ in range(n)]
        dp[0] = nums[0]
        for i in range(1,len(nums)):
            if(dp[i-1]<=0):
                return False
            dp[i] = max(dp[i-1]-1,nums[i])
        return True
```

类似 [Jump Game II](https://github.com/ZequnSong/Leetcode/blob/master/Leetcode/045JumpGameII.md) 的解法
```
class Solution {
    public boolean canJump(int[] nums) {
        for(int i = 0; i < nums.length;){
            if(nums[i] + i >= nums.length - 1) return true;         
            int max = -1;
            int index = i;          
            for(int j = i + 1; j <= nums[i] + i; j++){
                if(j + nums[j] > max){
                    max = j + nums[j];
                    index = j;
                }          
            }         
            if(index == i) return false;
            i = index;            
        }
        return true;
    }
}
```
