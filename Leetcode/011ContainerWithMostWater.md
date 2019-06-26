# Container With Most Water

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water. Return the maximum capacity.

**Note:** You may not slant the container and n is at least 2

![The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.](/pictures/question_11.jpg)

**Example:**
```
Input: [1,8,6,2,5,4,8,3,7]
Output: 49
```

O(n^2)思路：

* i和j为容器左右边界，初始为0和1
* 容量为i和j之中的**短板长度**乘以i和j的距离
* 循环计算每种情况的容量，若大于目前最大值volum，更新volum
* 返回volum

```
class Solution {
    public int maxArea(int[] height) {
        int volum = 0;

        for(int i = 0; i < height.length - 1; i++){
            for(int j = i + 1; j < height.length; j++){
                int tmp = Math.min(height[i],height[j])*(j-i);
                if(tmp>volum){
                    volum = tmp;
                }
            }
        }
        return volum;
        
    }
}
```

O(n)思路：

* left和right为容器左右边界，初始为0和height.length - 1，即最左和最右
* 设初始情形容量最大，此时有最大底边 right-left = height,length
* 若存在更大容量的情况，只能让目前left和right中的短板向中间移动来尝试
* 遍历直至left和right相遇

```
class Solution {
    public int maxArea(int[] height) {
        int volum = 0;
        int left=0, right = height.length - 1;
        while(left<right){
            volum = Math.max(volum, Math.min(height[left],height[right])*(right-left));
            if(height[left]<height[right])
                left++;
            else
                right--;
        }
        return volum;
    }
}
```
