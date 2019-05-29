/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:
Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:
Input: num1 = "123", num2 = "456"
Output: "56088"

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.


求两个字符串数字的相乘，输入的两个数和返回的数都是以字符串格式存储
本题这样做的目的可能是这样可以计算超大数相乘，可以不受int或long的数值范围的约束，
方法：
小时候学过多位数的乘法过程，每位相乘然后错位相加
把错位相加后的结果保存到一个一维数组中，然后分别每位上算进位，最后每个数字都变成一位，然后要做的是去除掉首位0，最后把每位上的数字按顺序保存到结果
*/
class Solution {
    public String multiply(String num1, String num2) {
        if(num1 == null || num2 == null) return "0";
        int len1 = num1.length(), len2 = num2.length();
        int[] digits = new int[len1+len2];
        //变长用StringBuilder
        StringBuilder res = new  StringBuilder();
        for(int i = len1 - 1; i >= 0; i--){
            for(int j = len2 - 1; j >= 0; j--){
                //ij位乘积
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                //加上之前本位和
                int sum = product + digits[i + 1 + j + 1 - 1];
                //进位
                digits[i + 1 + j + 1 - 2] += sum / 10;
                //本位取余，保留个位数
                digits[i + 1 + j + 1 - 1] = sum % 10;
            }       
        }
        //int值转为String
        for(int i = 0; i < digits.length; i++){
            if(!(res.length() == 0 && digits[i] == 0))
                res.append(digits[i]);       
        }
       //防止0*0情况，res为空 
       return res.length() == 0 ? "0" : res.toString();
    }
}
