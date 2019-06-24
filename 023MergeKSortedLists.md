# Merge k Sorted Lists

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

**Example:**
```
Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
```

**Method 1: PriorityQueue**

* if lists is null or length equals to zero, return **null**;
* saving all nodes into PriorityQueue **pq** in ascending order;
* create node **dummy** to save the result, create a pointer **head** to note the current position
* if there are more than one nodes in the queue
  * poll out one node at each time, then this node has the smallest value
  * if this node has next, put the rest node.next back to queue
  * connect this node to the **head.next**, then move pointer head to the next
* if there is one nodelist left, connect all of it to the head
* return dummy.next
  

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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>(cmp);
        
        /*
        * lambda 
        * (l1,l2)-> l1.val - l2.val
        * (l1,l2)-> l1.val > l2.val ? 1 : -1
        */
        
        
        for (ListNode node: lists) {
            if (node != null) {
                pq.add(node);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        
        while (pq.size() > 1) {
            //get the smallest node
            ListNode node = pq.poll();
            //if this node has next, put the rest back to queue
            if (node.next != null) {
                pq.add(node.next);
            }
            //connect this node to dummy
            head.next = node;
            head = head.next;
        }
        //put the rest node into dummy
        head.next = pq.poll();
        
        return dummy.next;
    }
    
    public static Comparator<ListNode> cmp=new Comparator<ListNode>() {
        public int compare(ListNode l1, ListNode l2) {
            if(l1.val>l2.val){
                return 1;
            }
            else if(l1.val<l2.val){
                return -1;
            }
            else return 0;
        }
    };
}
```
