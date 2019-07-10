# ZigZag Conversion

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
```
P   A   H   N
A P L S I I G
Y   I   R
```
And then read line by line: "PAHNAPLSIIGYIR"

**Example 1:**
```
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
```
**Example 2:**
```
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
```
---

**思路：**

规律：
* 除了第一行和最后一行，中间行会有形成之字型的数字，即每穿过一次v型，会有两个数字
* 而第一行中相邻两个元素的index之差，即一个完整之字长度，跟行数是相关的，为 2*numRows - 2,
* 根据这个特点，我们可以按顺序找到所有第一行和最后一行的元素
* 对于中间第i行会有形成之字型的数字，若第一个数的位置为j，则第二个数的位置为 j + 2*nRows-2 - 2*i,
* 一次性的把它们按顺序都加到新的字符串里面

```
class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1) return s;
        StringBuilder sb = new StringBuilder("");
        
        //first row
        for(int j = 0; j < s.length(); j = j + 2*numRows-2)
            sb.append(s.charAt(j));
        
        if(numRows > 2){
            for(int j = 1; j < numRows - 1; j++){
                for(int k = j; k < s.length(); k = k + 2*numRows-2){
                    sb.append(s.charAt(k));
                    if(k+2*numRows-2-2*j < s.length())
                        sb.append(s.charAt(k+2*numRows-2-2*j));       
                }
            }
        }
        
        //last row
        for(int j = numRows-1; j < s.length(); j = j + 2*numRows-2)
            sb.append(s.charAt(j));
        
        return sb.toString();
    }
}
```
