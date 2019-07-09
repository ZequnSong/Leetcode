# Count and Say

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
                tmpres.append(String.valueOf(count*10 + (str.charAt(i) - '0')));
                count = 1;
            }
            if( i == str.length() - 2)
                tmpres.append(String.valueOf(count*10 + (str.charAt(i+1) - '0')));
        }
        return tmpres.toString();          
    }
}
```
