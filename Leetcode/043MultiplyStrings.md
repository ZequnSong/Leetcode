# Multiply Strings

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

**Example 1:**
```
Input: num1 = "2", num2 = "3"
Output: "6"
```

**Example 2:**
```
Input: num1 = "123", num2 = "456"
Output: "56088"
```
**Note:**
1. The length of both num1 and num2 is < 110.
2. Both num1 and num2 contain only digits 0-9.
3. Both num1 and num2 do not contain any leading zero, except the number 0 itself.
4. You must not use any built-in BigInteger library or convert the inputs to integer directly.

---

**思路：**

手算多位数的乘法过程，都是每位相乘然后错位相加，用这种方法

<img src="/pictures/question_43.jpg" width="200">

* 两数相乘得到的乘积的长度其实正好是两个数字的长度之和，若 num1 长度为m，num2 长度为n, 则 num1 x num2 的长度为 m+n
* 新建数组mres，存储中间结果
  * 由于我们要从个位上开始相乘，所以从 num1 和 num2 字符串的尾部开始往前遍历
  * 确定相乘后的两位数所在的位置 p1 和 p2，由于 p2 相较于 p1 是地位，所以我们将得到的两位数 mul 先加到 p2 位置上去，这样可能会导致 p2 位上的数字大于9，所以我们将十位上的数字要加到高位 p1 上去，只将余数留在 p2位置，这样每个位上的数字都变成一位
* 依次将mres中的数加入到sb字符串中
* leading zeros 要跳过, 最后返回结果

```
class Solution {
    public String multiply(String num1, String num2) {  
        int m = num1.length();
        int n = num2.length();        
        int[] mres = new int[m + n];
        
        for(int i = m - 1; i >= 0 ; i--){
            for(int j = n - 1; j >= 0; j--){
                mres[i + j + 1] += (num1.charAt(i) - '0')*(num2.charAt(j) - '0');               
                if(mres[i+j+1] > 9){
                    mres[i + j] += mres[i + j + 1]/10;
                    mres[i + j + 1] = mres[i + j + 1]%10;             
                }                             
            }
        }        

        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < mres.length; i++)
            sb.append(mres[i]);        
        while(sb.length()>1 && sb.charAt(0)=='0'){
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
```
