# Remove Duplicates from Sorted List II

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

**Example 1:**
```
Input: 1->2->3->3->4->4->5
Output: 1->2->5
```

**Example 2:***
```
Input: 1->1->1->2->3
Output: 2->3
```

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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, next = null, current = head;
        while(current != null && current.next != null){
            next = current.next;
            
            if(next.val != current.val){
                pre = current;
                current = next;
            }else{
                while(next.next != null && next.next.val == current.val){
                    next = next.next;
                }
                current = next.next;
                pre.next = current;
            }
        }
        return dummy.next;
        
    }
}
```
