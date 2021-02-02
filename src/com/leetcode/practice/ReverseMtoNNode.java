package com.leetcode.practice;

public class ReverseMtoNNode {
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
        ListNode head = lst1;

        while(head!=null){
            System.out.print("\t" + head.val);
            head = head.next;
        }
        head = lst1;
        ListNode reverseHead = getreversednodes(head,3,5);



        while(reverseHead != null) {
            System.out.print("\tnode val = " + reverseHead.val);
            reverseHead = reverseHead.next;
        }


    }

    public static ListNode getreversednodes(ListNode head, int m, int n){
        if(head == null || head.next == null)
            return head;
        ListNode prev,curr,next,end;
        prev = head;
        int i = 1;
        while(i<m-1){ // why is it even less than m-1
            i++;
            prev = prev.next;
        }
        curr = prev.next;
        next = curr.next;
        ListNode beforeprev = prev;
        ListNode reversetail = curr;
        ListNode newPrev = null;

        while(i < n ){ // Counting of the nodes, it should always be less than
            next = curr.next;
            curr.next = newPrev;
            newPrev = curr;
            curr = next;
            i++;
           // next = next.next;
        }
        beforeprev.next = newPrev;
        reversetail.next = curr;


        return head; // remove when not needed
    }
}
