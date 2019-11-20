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
**思路：**
* 处理链表问题，一般在开头加dummy节点，以防止头节点丢失
* pre 作为每组节点的头节点
* cur指针每移动k次，说明pre(不包括pre)和cur(包括cur)之间有k个节点，对这组节点进行反转， 更新pre和cur
* 不够k次时，pre不动

* 反转函数：将startAfter和endBefore之间的节点反转
  * startAfter -> n1 -> n2 -> n3 ... nk -> endBefore
  * //=> 翻转之后
  * startAfter -> nk -> nk-1 -> ... -> n1 -> endBefore
  * 和[Reverse Linked List](https://github.com/ZequnSong/Leetcode/blob/master/Leetcode/206.%20Reverse%20Linked%20List.md)不同的是我们要返回n1作为新的pre，而不是返回反转后的新头节点nk，我们可以一开始直接存储n1 = startAfter.next，反转结束后返回该节点即可
  
```
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        for(int i = 1; cur != null; i++){
            if(i % k == 0){
                pre = reverse(pre, cur.next);
                cur = pre.next;
            }
            else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    
    private ListNode reverse(ListNode startAfter, ListNode endBefore){
        ListNode pre = startAfter, cur = pre.next, next = null;
        ListNode res = startAfter.next;
        while(cur!=endBefore){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        startAfter.next.next = endBefore;
        startAfter.next = pre;
        return res;
    }
}
```
