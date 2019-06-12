# Merge Two Sorted Lists

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

**Example:**
```
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
```


思路：
* 当l1和l2都有值，则比较值大小，将小的点加入res，然后从原链表中去掉该点
* 当l1或l2为空时，另一侧链表残余值加入res

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode current = res;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                current.next = l1;
                l1 = l1.next;
            }else{
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        if(l1 != null){
            current.next = l1;
        }else
            current.next = l2;
        return res.next;
        
    }
}
```

思路：递归
```
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
```
