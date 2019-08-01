# Jump Game

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
这道题说的是有一个非负整数的数组，每个数字表示在当前位置的基础上最多可以走的步数，求判断能不能到达最后一个位置，开始我以为是必须刚好到达最后一个位置，超过了不算，其实是理解题意有误，因为每个位置上的数字表示的是最多可以走的步数而不是像玩大富翁一样摇骰子摇出几一定要走几步。那么我们可以用动态规划Dynamic Programming来解，我们维护一个一位数组dp，其中dp[i]表示达到i位置时剩余的步数，那么难点就是推导状态转移方程啦。我们想啊，到达当前位置的剩余步数跟什么有关呢，其实是跟上一个位置的剩余步数和上一个位置的跳力有关，这里的跳力就是原数组中每个位置的数字，因为其代表了以当前位置为起点能到达的最远位置。所以当前位置的剩余步数（dp值）和当前位置的跳力中的较大那个数决定了当前能到的最远距离，而下一个位置的剩余步数（dp值）就等于当前的这个较大值减去1，因为需要花一个跳力到达下一个位置，所以我们就有状态转移方程了：dp[i] = max(dp[i - 1], nums[i - 1]) - 1，如果当某一个时刻dp数组的值为负了，说明无法抵达当前位置，则直接返回false，最后循环结束后直接返回true即可，代码如下：

```
class Solution {
    public boolean canJump(int[] nums) {
        int[] dp = new int[nums.length];
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(dp[i-1], nums[i-1]) - 1;
            if(dp[i] < 0) return false;
        }
        return true;
    }
}
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
