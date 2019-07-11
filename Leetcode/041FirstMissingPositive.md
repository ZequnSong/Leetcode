# First Missing Positive


时间复杂度O(n),空间复杂度O(1)




时间复杂度O(n),空间复杂度O(n)
```
class Solution {
    public int firstMissingPositive(int[] nums) {
        boolean[] tmp = new boolean[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0 && nums[i] <= tmp.length)
                tmp[nums[i]-1] = true;
        }
        
        for(int i = 0; i < tmp.length; i++){
            if(tmp[i] == false)
                return i+1;
        }     
        return nums.length + 1;
    }
}
```
