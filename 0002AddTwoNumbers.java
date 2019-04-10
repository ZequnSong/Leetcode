/**
 *You are given two non-empty linked lists representing two non-negative integers. 
 *The digits are stored in reverse order and each of their nodes contain a single digit. 
 *Add the two numbers and return it as a linked list.
 *You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *-------------------------------------------------------------------------------------------
 *Example:
 *Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *Output: 7 -> 0 -> 8
 *Explanation: 342 + 465 = 807.
 *-----------------------------------------------------------------------------------------
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        int sum = 0, carry = 0;
        ListNode current = res;
        while(l1!= null || l2!= null){
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            current.next = new ListNode(sum%10);
            carry = sum>=10 ? 1 : 0;
            current = current.next; 
            sum = 0;
        }
        if(carry == 1){
            current.next = new ListNode(1);
        }
        return res.next;        
    }
}
