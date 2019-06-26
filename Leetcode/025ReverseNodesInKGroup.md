# Reverse Nodes in k-Group

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

**Example:**
```
Given this linked list: 1->2->3->4->5
For k = 2, you should return: 2->1->4->3->5
For k = 3, you should return: 3->2->1->4->5
```

**Note:**
* Only constant extra memory is allowed.
* You may not alter the values in the list's nodes, only nodes itself may be changed.

---
**思路1：**
* 处理链表问题，一般在开头加dummy节点，以防止头节点丢失
* pre 作为每组节点的头节点
* cur指针每移动k次，说明pre(不包括pre)和cur(包括cur)之间有k个节点，对这组节点进行反转
* 反转函数：
  * last指针指向该组节点的开头，即pre.next，反转过后该节点会变成该组最后一个节点，故名为last
  * 每次将last后的一个节点cur反转到前面，即pre和last之间，如：
  * -1-->1-->2-->3-->4-->5-->6-->next
  * pre &nbsp;last &nbsp;cur
  * -1-->2-->1-->3-->4-->5-->6-->next
  * pre &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    last &nbsp;cur
  * 直到cur等于next为止，则完成了一组的反转

* 反转结束后，更新pre为上组反转后的最后一个节点last，更新cur为pre.next，继续移动cur

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode pre = dummy, cur = head;
        
        for(int i = 1; cur != null; i++){
            if(i % k == 0){
                pre = reverseOneGroup(pre, cur.next);
                cur = pre.next;
            }
            else{
                cur = cur.next;
            }
        }
        
        return dummy.next;
        
    }
    
    ListNode reverseOneGroup(ListNode pre, ListNode next){
        ListNode last = pre.next, cur = last.next;
        while(cur != next){
            last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = last.next;
        }
        return last;
    }
}
```
