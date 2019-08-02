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
                //res.add(newInterval);    
                break;
            }
            else if(intervals[i][0] == newInterval[1])
                newInterval[1] = intervals[i][1]; 
            else if(intervals[i][0] >= newInterval[0]){
                if(intervals[i][1] <= newInterval[1])
                    continue;
                else
                    newInterval[1] = intervals[i][1];
            }
            else if(intervals[i][0] <= newInterval[0]){
                if(intervals[i][1] >= newInterval[1])
                    break;
                else
                    newInterval[0] = intervals[i][0];
            }
        }
            res.add(newInterval);    
        for(; i < intervals.length; i++)
            res.add(intervals[i]);
        
        return res.toArray(new int[res.size()][]);
            
    }
}
```
