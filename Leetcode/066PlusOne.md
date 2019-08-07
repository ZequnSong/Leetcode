# Plus One

Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

**Example 1:**
```
Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
```

**Example 2:**
```
Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
```
* 很简单，向最后一位加一个1，若等于10，向前进位，然后依次判断上一位是否为10
* 若不是，直接返回
* 若一直到首位都为10(说明给的数是999...)，则新建个空间大一位的数组，首位赋1，返回

```
class Solution {
    public int[] plusOne(int[] digits) {
        int m = digits.length - 1;
        digits[m]++;
        for(int i = m; i >= 0; i--){
            if(digits[i] == 10){
                if(i == 0) break;
                digits[i] = 0;
                digits[i-1]++;
            }
            else
                return digits;
        }
        
        int[] res = new int[digits.length+1];
        res[0] = 1;
        return res;
    }
}
```
