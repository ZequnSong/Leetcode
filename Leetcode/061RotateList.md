# Rotate List

Given a linked list, rotate the list to the right by k places, where k is non-negative.

**Example 1:**
```
Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
```

**Example 2:**
```
Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
```

思路： 

题意是将链表右移n步，每移出一个都接到表头

* 先获取链表长度len，同时得到指向尾部的指针cur
* 当k远大于len时，对其取余k%len才是我们真正要移动的步数
* end指针向前移动至len-k位置，此处为新链表的末尾，那么新链表的头newHead就是end.next
* 将右半段 newHead 直到 cur 的部分移动到head前面即可

```
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(k == 0 || head == null) return head;
        ListNode cur = head;
        int len = 1;
        while(true){
            if(cur.next != null){
                cur = cur.next;
                len++;
            }
            else
                break;           
        }
        if(k>=len)
            k = k%len;
        if(k == 0) return head;
        ListNode end = head, newHead = head;
        for(int i = 1; i < len-k; i++){
            end = end.next;
        }
        newHead = end.next;
        end.next = null;
        cur.next = head;
        return newHead;
    }
}
```
