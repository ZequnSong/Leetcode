# Swap Nodes in Pairs

Given a linked list, swap every two adjacent nodes and return its head.

You may **not** modify the values in the list's nodes, only nodes itself may be changed.

```
Example:
Given 1->2->3->4, you should return the list as 2->1->4->3.
```


**Method 1: non-recursive, Three pointers:**
* before

...->pre->left->right->...

* after

...->pre->right->left->...



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
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = head;
        
        ListNode left, right;
        
        while(pre.next != null && pre.next.next != null){
            left = pre.next;
            right = pre.next.next;
            
            pre.next = right;
            left.next = right.next;
            right.next = left;
            pre = left;
        }
        return dummy.next;
    }
        
}
```

**Method 2: recursive**

* before

...->head->temp->...

* after

...->temp->head->...

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
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode temp = head.next;
        head.next = temp.next;
        temp.next = head;
        head.next = swapPairs(head.next);

        return temp;
    }
        
}
```
