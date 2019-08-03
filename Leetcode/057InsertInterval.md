# Insert Interval

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

**Example 1:**
```
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
```

**Example 2:**
```
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
```

思路：对给定的区间集进行一个一个的遍历比较，那么会有两种情况，重叠或是不重叠

1. 不重叠的情况最好，直接将当前区间插入到res
2. 重叠的情况比较复杂，有时候会有多个重叠，我们需要更新**新区间newInterval**的范围以便包含所有重叠，之后将新区间加入结果res
3. 一旦新区间安放完毕，将后面的区间依次加入结果 res 即可 

* 若给定区间为空，则直接返回新区间
* 用i记录遍历到的位置，以便插入新区间后，将剩下区间加入res
* 遍历给定区间
  * 若不重叠，有两种情况：
    * 该区间在新区间前面，直接将当前区间插入到res
    * 该区间在新区间后面，直接将新区间插入到res，然后break，将剩下的区间依次加入res即可
  * 若重叠，有四种情况：
    * 该区间左边位于新区间内
      * 若右边位于新区间内，说明该区间被新区间完全吞掉了，可以跳过
      * 若右边位于新区间外，将新区间的右边扩更为该区间的右边
    * 该区间左边位于新区间外
      * 若右边位于新区间外，说明该区间把新区间完全吞掉了，即新区间不会对原区间组产生影响，直接返回原给定区间组intervals即可
      * 若右边位于新区间内，将新区间左边扩更为该区间的左边
* 若遍历到最后一个区间，说明新区间后再无区间，将新区间加入后返回res即可

<img src="/pictures/question_57.jpg" width="400">

```
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        if(intervals.length == 0){
            res.add(newInterval);
            return res.toArray(new int[1][]);
        }
        int i = 0;
        for(i = 0; i < intervals.length; i++){
            if(intervals[i][1] < newInterval[0])
                res.add(intervals[i]);
            else if(intervals[i][0] > newInterval[1]){
                res.add(newInterval);    
                break;
            }            
            else if(intervals[i][0] >= newInterval[0] && intervals[i][1] >= newInterval[1])
                    newInterval[1] = intervals[i][1];
            else if(intervals[i][0] <= newInterval[0]){
                if(intervals[i][1] >= newInterval[1])
                    return intervals;
                else
                    newInterval[0] = intervals[i][0];
            }     
        }
        if(i == intervals.length)
            res.add(newInterval);
        else{
            for(; i < intervals.length; i++)
                res.add(intervals[i]);
        }           
        return res.toArray(new int[res.size()][]);            
    }
}
```
