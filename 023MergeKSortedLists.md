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
**Method 2: Divide and Conquer **

对半划分
k个链表先划分为合并两个k/2个链表的任务，再不停的往下划分，直到划分成只有一个或两个链表的任务，开始合并

比如合并6个链表，首先分别合并0和3，1和4，2和5。
下一次只需合并3个链表，我们再合并1和3，最后和2合并就可以了。

n = lists.length
k = (n+1)/2 加1是为了当n为奇数的时候，k能始终从后半段开始，比如

* when n is odd (e.g n = 5, k = 3)
[0], [1], [2], [3], [4]

i = 0, i < n/2  => i < 2
[0] merge with [0+k]=[3]
[1] merge with [1+k]=[4]

[2] left with no actions

* when n is even (e.g n = 6, k = 3)
[0], [1], [2], [3], [4], [5]

i = 0, i < n/2  => i < 3
[0] merge with [0+k]=[3]
[1] merge with [1+k]=[4]
[2] merge with [2+k]=[5]


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
        
        int n = lists.length;
        while(n > 1){
            int k = (n + 1) / 2;
            for( int i = 0; i < n/2; i++){
                lists[i] = mergeTwoLists(lists[i], lists[i+k]);
            }
            n = k;
        }        
        return lists[0];        
    }
    
    ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
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
        if(l1 != null)
            current.next = l1;
        else
            current.next = l2;
        return dummy.next;
        
    }
}
```
