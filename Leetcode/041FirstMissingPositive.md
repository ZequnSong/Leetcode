# First Missing Positive


* 时间复杂度O(n),空间复杂度O(1)

* 不能建立新的数组，那么只能覆盖原有数组
* 思路是先遍历一遍数组，把1放在数组第一个位置nums[0]，2放在第二个位置nums[1]，即需要把nums[i]放在nums[nums[i] - 1]上
* 即用nums[i]表示i+1是否存在，若存在，nums[i] = i + 1
* 最后再遍历一遍数组，如果对应位置上的数不正确(nums[i] != i + 1)则返回正确的数(i+1)，若全正确则返回nums.length+1

**步骤**
* 新建一个tmp用于交换两数位置

* 遍历整个数组，若nums[i]为正数且不超过nums.length，将其移动到正确位置，具体步骤如下：
* 第一遍遍历nums数组
  * 若当前nums[i]等于i+1，则说明值i+1已经在正确的位置i上，跳过
  * 若当前nums[i]小于等于0，直接跳过
  * 否则说明当前数值nums[i] 为正，小于等于n且不在正确位置（即nums[i]!=i+1）
    * 若对应正确位置nums[nums[i]-1]上已经有了正确数值nums[i]，则说明当前nums[i]是多余重复项，跳过
    * 否则，正确位置nums[nums[i]-1]上还没有正确数值nums[i]，互换nums[nums[i]-1]和nums[i]的值，继续（此时i不能加1，因为交换来的新nums[i]的值仍需要处理）
  * 否则说明当前数值过大，超出nums.length，同样跳过不予处理（因为若全是过大的正数，最后直接返回1，对结果无影响）

* 最后再遍历一遍数组，如果对应位置上的数不正确(nums[i] != i+1)则返回正确的数i+1,，若全正确则返回nums.length+1
```
class Solution {
    public int firstMissingPositive(int[] nums) {
        int tmp = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == i + 1 || nums[i] <= 0)
                continue;
            
            if(nums[i] <= nums.length){
                if(nums[nums[i]-1] == nums[i])
                    continue;
                
                tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
                i--;
            }
        }
        
        for(int i = 0; i < nums.length; i++)
            if(nums[i] != i+1)
                return i+1;
        
        return nums.length + 1;    
    }
}
```

* 时间复杂度O(n),空间复杂度O(n)
* 同样思路，新建了一个数组来判断每个值是否存在
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
