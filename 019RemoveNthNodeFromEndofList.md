# Remove Nth Node From End of List

Given a linked list, remove the n-th node from the end of list and return its head.

**Example:**
```
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
```

**Note:**

Given n will always be valid.

**Follow up:**

Could you do this in one pass?

思路：只遍历一遍，双指针

* 建立指针pre和cur，指向head
* n保证有效，即不会超过链表长度，使cur遍历n步
  * 若cur为空，则意味着n等于链表长度，删除的是表头
  * 若cur不为空，此时pre从head开始随同cur一起遍历下去，直到cur.next指向空，删除pre.next即可
  

1->2->3->4->5, and n = 2.

1->2->3->4->5, after move 2 steps, cur = 3
      ↑
     cur
     
1->2->3->4->5->null, after move 2 steps, cur = 3
      ↑     ↑
     pre   cur



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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head, cur = head;
        while(n > 0){
            cur = cur.next;
            n--;
        }
        if(cur = null)
            return head.next;
        else{
            while(cur.next != null){
                cur = cur.next;
                pre =  pre.next;
            }
        }
        pre.next = pre.next.next;
        return head;

    }
}
 ```
 
 
 
