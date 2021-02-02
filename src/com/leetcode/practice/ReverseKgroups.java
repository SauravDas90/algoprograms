package com.leetcode.practice;
class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}
public class ReverseKgroups {

    public static void main(String[] args) {
        ListNode lst1= new ListNode(1);
        ListNode lst2= new ListNode(2);
        ListNode lst3= new ListNode(3);
        ListNode lst4= new ListNode(4);
        ListNode lst5= new ListNode(5);
        ListNode lst6= new ListNode(6);
        ListNode lst7= new ListNode(7);

        lst1.next = lst2;
        lst2.next = lst3;
        lst3.next = lst4;
        lst4.next = lst5;
        lst5.next = lst6;
        lst6.next = lst7;
        ListNode reversed = reverseKGroup(lst1,3);
        while(reversed.next!=null){
            reversed = reversed.next;
            System.out.print("\t" + reversed.val);
        }

    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        int n = 0;
        for (ListNode i = head; i != null; n++, i = i.next);

        ListNode dmy = new ListNode(0);
        dmy.next = head;
        for(ListNode prev = dmy, tail = head; n >= k; n -= k) {
            for (int i = 1; i < k; i++) {
                ListNode next = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = next;
            }

            prev = tail;
            tail = tail.next;
        }
        return dmy.next;
    }

}
