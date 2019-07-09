# Count and Say

The count-and-say sequence is the sequence of integers with the first five terms as following:
```
1.     1
2.     11
3.     21
4.     1211
5.     111221
```
1 is read off as "one 1" or 11.

11 is read off as "two 1s" or 21.

21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.
 
**Example 1:**
```
Input: 1
Output: "1"
```
**Example 2:**
```
Input: 4
Output: "1211"
```
---

**Method 1: recusive**

* 从1开始自下而上求n的结果
* 当n == 1 或 2 直接返回 1 或 11 
* 根据n= k-1的结果求n=k的结果
  * 若当前数字和下一个数字相等，计数加1
  * 若当前数字和下一个数字不等，将该数字出现次数count和该数字依次加入字符串中
  * 将最后一个字符的出现次数count和该数字依次加入字符串中
  * 将得到的字符串作为当前n=k的结果

```
class Solution {
    public String countAndSay(int n) {
        if(n == 1) return "1";
        if(n == 2) return "11";
        String str = countAndSay(n-1);
        StringBuilder tmpres = new StringBuilder("");
        int count = 1;
        for(int i = 0; i < str.length() - 1; i++){
            if(str.charAt(i) == str.charAt(i+1)){
                count++;
            }else{
                tmpres.append(count);
                tmpres.append(str.charAt(i));                
                count = 1;
            }
            if( i == str.length() - 2){
                tmpres.append(count);
                tmpres.append(str.charAt(i+1));
            }
        }
        return tmpres.toString();          
    }
}
```

**Method 2:**
非递归方法， 思路一样
```
class Solution {
    public String countAndSay(int n) {
        if(n == 1) return "1";
        String s = "1";
        while(n> 1){
            StringBuilder sb  =new StringBuilder("");
            int count = 1;
            for(int i = 1; i < s.length(); i++){
                if(s.charAt(i) == s.charAt(i-1))
                    count++;
                else{
                    sb.append(count);
                    sb.append(s.charAt(i-1));
                    count = 1;
                }
            }
            sb.append(count);
            sb.append(s.charAt(s.length()-1));   
            s = sb.toString();
            n--;
        }
        return s;
    }
}
```
