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

思路：

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
