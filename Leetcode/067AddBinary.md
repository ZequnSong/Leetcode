# Add Binary

Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

**Example 1:**
```
Input: a = "11", b = "1"
Output: "100"
```

**Example 2:**
```
Input: a = "1010", b = "1011"
Output: "10101"
```

涉及结果不定的string问题，最好从后往前求

* 从后往前依次求值，该进位的进位
* 最后若carry为1，就在尾部加个1，然后反转即为结果


```
class Solution {
    public String addBinary(String a, String b) {
        int aLen = a.length() - 1, bLen = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while(aLen >= 0 || bLen >= 0){
            int tmp = carry;
            if(aLen >= 0)
                tmp += a.charAt(aLen--) - '0';
            if(bLen >= 0)
                tmp += b.charAt(bLen--) - '0';
            if(tmp >= 2){
                tmp = tmp - 2;
                carry = 1;
            }else
                carry = 0;
            sb.append(tmp);
        }
        if(carry == 1)
            sb.append(carry);
        return sb.reverse().toString();
    }
}
```
