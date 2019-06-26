# Roman to Integer

Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

**Example 1:**
```
Input: "III"
Output: 3
```
**Example 2:**
```
Input: "IV"
Output: 4
```
**Example 3:**
```
Input: "IX"
Output: 9
```
**Example 4:**
```
Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
```
**Example 5:**
```
Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
```
思路： 类似于 [012 Integer to Roman](https://github.com/ZequnSong/Leetcode/blob/master/012IntegertoRoman.md).
* 列出所有字母表示数值和减法表示的特殊数值，从大到小排列
* 将给定字符串与已列出字符串依次比较，若等于，将对应数值加入res，直至给定字符串变为0
```
class Solution {
    public int romanToInt(String s) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] strs = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"}; 
        int res = 0, i = 0;
        while(i < strs.length && s.length() != 0){
            if(s.substring(0,1).equals(strs[i])){
                res = res + values[i];
                s = s.substring(1);
            }else if(s.length() > 1 && s.substring(0,2).equals(strs[i])){
                res = res + values[i];
                s = s.substring(2);
            }
            else 
                i++;
        }
        return  res;      
    }
}
```
