# Valid Number

Validate if a given string can be interpreted as a decimal number.

```
Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false
```
正则表达式

```
class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        if(s.isEmpty()){
        	return false;
        }
        String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";
        if(s.matches(regex))
        	return true;
        else
            return false;
        
    }
}
```
