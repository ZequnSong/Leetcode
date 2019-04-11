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
 --------------------------------------------------------------------------------------------
 * solution: 
 * two pointers one point l1, another point l2
 * the sum of each position = l1.val + l2.val + carry(from the last loop)
 * save sum%10 to the result linkNode
 * if sum >= 10, let carry = 1
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        int sum = 0, carry = 0;
        ListNode current = res;
        //two pointers, one point l1, another point l2
        while(l1!= null || l2!= null){
            //the sum of each position = l1.val + l2.val + carry(from the last loop)
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            //save sum%10 to the result linkNode
            current.next = new ListNode(sum%10);
            //if sum >= 10, let carry = 1
            carry = sum>=10 ? 1 : 0;
            //move the pointer to next
            current = current.next; 
            //reset sum
            sum = 0;
        }
        //if l1 & l2 are null, and carry still = 1
        if(carry == 1){
            current.next = new ListNode(1);
        }
        return res.next;        
    }
}
