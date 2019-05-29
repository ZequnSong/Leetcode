/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/
//------------------------------------------------------------------------------------
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){ 
                if(tempList.contains(nums[i])) continue; 
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    } 
}
//-----------------------------------------------------------------------------------
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, 0, nums);
        return list;
    }
    private void backtrack(List<List<Integer>> list, int start, int [] nums){
        if(start == nums.length){
            List<Integer> temp = new ArrayList<Integer>();
            for(int i : nums){
                temp.add(i);
            }
            list.add(new ArrayList<>(temp));
           
        } else{
            for(int i = start; i < nums.length; i++){ 
                swap(nums, start, i);
                backtrack(list, start+1, nums);
                swap(nums, start, i);
            }
        }
    } 
    private void swap(int[] nums,int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] =  temp;
    }
}
