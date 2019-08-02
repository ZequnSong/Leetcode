# Merge Intervals

Given a collection of intervals, merge all overlapping intervals.

**Example:**
```
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
```
思路:

* 首先给区间集排序，由于要排序的是数组，所以要定义自己的 comparator，才能用 sort 来排序，这里使用lambda表达式更加方便
* 开始合并，从第二个开始遍历区间集
  * 如果当前区间的start小于等于上一个区间的end，说明有重叠，将当前区间的end更新为两个end中最大值，start更新为两个start中最小值(保证合并后区间最大)
  * 若当前区间和上一个区间无重叠，直接将上一个区间存入结果中
  * 最后，无论是否重叠，最后一个区间还没有加入结果中，将其加入res后返回

```
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1) return intervals;
        
        Arrays.sort(intervals,(i1, i2)->Integer.compare(i1[0],i2[0]));
        
        List<int[]> res = new ArrayList<>();
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] <= intervals[i-1][1]){
                intervals[i][1] = Math.max(intervals[i][1],intervals[i-1][1]);
                intervals[i][0] = Math.min(intervals[i][0],intervals[i-1][0]);
            }
            else{
                res.add(intervals[i-1]);
            }      
        }
        res.add(intervals[intervals.length-1]);
    
        return res.toArray(new int[res.size()][]);
    }
}
```
