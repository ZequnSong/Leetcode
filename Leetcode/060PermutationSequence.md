# Permutation Sequence

The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

```
"123"
"132"
"213"
"231"
"312"
"321"
```

Given n and k, return the k-th permutation sequence. (n will be between 1 and 9 inclusive)

**Example :**
```
Input: n = 3, k = 3
Output: "213"
```

O(n^2)思路：

不用将所有的排列组合的情况都求出来, 只求出第k个排列组合即可，

当n = 4时，其排列组合共有4! = 24种，以n = 4, k = 17的情况来分析，所有排列组合情况如下：

```
排列组合nums： 1->2->3->4
-------------------------------
首位 | 之后的排列组合 | 对应(k-1)的范围 
1       (2,3,4)        0----5  
2       (1,3,4)        6----11
3       (1,2,4)       12----17
4       (1,2,3)       18----23 
```

求首位：

* 首位之后有4-1个数，这些数共有(4-1)!种排列方式，用(17-1)/(4-1)! = 2, 找num中下标为2的数为3，所以第17个组合应该位于 3 (1,2,4) 13---18 中，且首位为3。 

* 这里有个小trick，用k-1代替k，这样可以保证 即使k=(4-1)!，(6-1)/(4-1)! = 5/6 = 0 也会被归入第0层，防止多除出个1

```
排列组合nums： 1->2->4
-------------------------------
首位 | 之后的排列组合 | 对应(k-1)的范围 
1       (2,4)          0----1  
2       (1,4)          2----3
4       (1,2)          4----5
```

求之后的数位：

* 求完首位后，接下来还剩3个数，(1,2,4)，将3从num链表中去掉，则又变成了求首位的问题。

* 将k缩小为(17-1)%(4-1)! = 4，取余的原因，如果对这24个数以6个一组来分，那么k=16这个位置就是在第三组(k/6 = 2)中的第五个（k%6 = 4）数字，也就是(1,2,4)排列中的第5个组合。

* 那么从(1,2,4)中找第5个排列。4/(3-1)! = 2，找num中下标为2的数为4，所以第5个组合应该位于 4 (1,2) 4----5 中，且首位为4

依次求出之后的数位。



```
class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> num = new ArrayList<>();
        for (int i = 1; i <= n; i++) 
            num.add(i);
        
        int[] factorial = new int[n+1];
        factorial[0] = 1;
        for(int i = 1; i < n; i++)
            factorial[i] = factorial[i-1] * i;
        
        k = k-1;
        StringBuilder sb = new StringBuilder();    
        for(int i = 0; i < n; i++){
            int curNum = k / factorial[n-1-i];
            k = k % factorial[n-1-i];
            sb.append(num.get(curNum));
            num.remove(curNum);
        }
        return sb.toString();
    }    
}
```


简单思路：

递归，每完成一次排列，rank+1，直到rank等于第k个，返回

```
class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder("");
        boolean[] visited = new boolean[n];
        helper(n, k, 0, visited, sb);
        return sb.toString();
    }
    int helper(int n, int k, int rank, boolean[] visited, StringBuilder sb){   
        if(sb.length() == n)
            return ++rank;
        for(int i = 1; i <= n; i++){
            if(!visited[i-1]){
                visited[i-1] = true;
                sb.append(i);
                rank = helper(n, k, rank, visited, sb);
                if(rank != k){
                    sb.deleteCharAt(sb.length()-1);
                    visited[i-1] = false;
                }
                else return rank;
            }
        }
        return rank;
    }
    
}
```
