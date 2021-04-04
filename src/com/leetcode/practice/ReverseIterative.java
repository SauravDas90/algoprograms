package com.leetcode.practice;


public class ReverseIterative{

    public static void main(String[] args){ // while defining main method, provide String[] args
        LinkedNode lst1= new LinkedNode(1);
        LinkedNode lst2= new LinkedNode(2);
        LinkedNode lst3= new LinkedNode(3);
        LinkedNode lst4= new LinkedNode(4);
        LinkedNode lst5= new LinkedNode(5);
        LinkedNode lst6= new LinkedNode(6);
        LinkedNode lst7= new LinkedNode(7);

        lst1.next = lst2;
        lst2.next = lst3;
        lst3.next = lst4;
        lst4.next = lst5;
        lst5.next = lst6;
        lst6.next = lst7;

        LinkedNode head = lst1;
        // remember that before you iterate or change anything, just know to print it
        while(lst1 != null) {
            System.out.print("\tnode val = " + lst1.elem);
            lst1 = lst1.next;
        }
        System.out.println();
        LinkedNode reverseHead = reverse(head);



        while(reverseHead != null) {
            System.out.print("\tnode val = " + reverseHead.elem);
            reverseHead = reverseHead.next;
        }

    }

    public static LinkedNode reverse(LinkedNode head){
        if(head == null && head.next == null)
            return head;
        //System.out.println("reverse = "+ head);

        LinkedNode prev,curr,next;
        prev = null;
        curr = head;
        next= head.next;

        while(next!= null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
        /* ensure that, when you return, you send the correct head
        // at the end next, curr points to the null
        // so returning "curr" will be pointed to null
        */
    }

}

class LinkedNode{

    int elem;
    LinkedNode next;

    LinkedNode(){
        elem = 0;
    }

    LinkedNode(int elem ){
        this.elem = elem ;
    }

}

