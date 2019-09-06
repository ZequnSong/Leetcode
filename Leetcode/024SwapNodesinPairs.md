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
        if(head == null || head.next == null) return head;       
        ListNode dummy= new ListNode(0), pre = dummy, cur = head, next = head.next;
        dummy.next = head;  
        while(cur != null && next != null){
            cur.next = next.next;
            next.next = cur;
            pre.next = next;
            pre = cur;
            cur = cur.next;
            if(cur != null)
                next = cur.next;
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
