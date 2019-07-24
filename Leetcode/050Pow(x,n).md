# Pow(x, n)

Implement pow(x, n), which calculates x raised to the power n (xn).

**Example 1:**
```
Input: 2.00000, 10
Output: 1024.00000
```
**Example 2:**
```
Input: 2.10000, 3
Output: 9.26100
```

**Example 3:**
```
Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
```

**Note:**
n is a 32-bit signed integer

**思路1：**
* 用递归来折半计算，每次把n缩小一半，这样n最终会缩小到0，任何数的0次方都为1
* 再往回乘，如果此时n是偶数，直接把上次递归得到的值算个平方返回即可，如果是奇数，则还需要乘上个x的值。
* 注意n有可能为负数，对于n是负数的情况，可以取其倒数

```
class Solution {
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        double tmp = myPow(x, n/2);
        if(n > 0)    
            return n%2 == 0? tmp*tmp : tmp*tmp*x;
        else
            return n%2 == 0? tmp*tmp : tmp*tmp/x;

    }
}
```
**思路2：**
迭代做法

```
class Solution {
    public double myPow(double x, int n) {
        double res = 1;
        for(int i = n; i != 0; i/=2){
            if(i%2 != 0)
                res = res*x;
            x = x*x;
        }
        return n < 0 ? 1 / res : res;
    }
}
```
