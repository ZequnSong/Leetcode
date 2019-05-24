# Add Two Numbers

You are given two **non-empty** linked lists representing two non-negative integers. The digits are stored in **reverse order** and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

-----------------------------
**Example:**
```
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
```

```
Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
```
O(n)思路：
n = max(l1.length, l2.length)
* 新建ListNode res 用于存储结果，current指针用于向res.next添加新的数位
* 遍历l1 & l2
  * 若l1非空，l1.val加入sum中，l1变更为l1.next
  * 若l2非空，l2.val加入sum中，l2变更为l2.next
  * 来自上一轮进位值carry加入sum中，carry变更为sum/10
  * 利用current.next向res中添加新数位，current更为current.next
  * 重置sum为0
* 若遍历结束后，carry仍有非零值，则利用current.next向res中添加新数位
* 返回res.next

```
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        int sum = 0, carry = 0;
        ListNode current = res;
        while(l1!= null || l2!= null){
            //the sum of each position = l1.val + l2.val + carry(from the last loop)
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            //save sum%10 to the res
            current.next = new ListNode(sum%10);
            //if sum >= 10, let carry = 1
            carry = sum/10;
            //move the pointer to next
            current = current.next; 
            //reset sum
            sum = 0;
        }
        //if l1 & l2 are null, and carry still != 0
        if(carry != 0){
            current.next = new ListNode(carry);
        }
        return res.next;        
    }
}
```
