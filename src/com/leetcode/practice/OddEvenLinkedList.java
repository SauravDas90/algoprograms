package com.leetcode.practice;

public class OddEvenLinkedList {
    public static void main(String[] args) {

        LinkedList head = new LinkedList(2);
        LinkedList t2 = new LinkedList(1);
        LinkedList t3 = new LinkedList(3);
        LinkedList t4 = new LinkedList(4);
        LinkedList t5 = new LinkedList(6);
        LinkedList t6 = new LinkedList(7);
        LinkedList t7 = new LinkedList(29);
        //  LinkedList t8 = new LinkedList(3);
        head.next = t2;
        t2.next = t3;
        t3.next = t4;
        t4.next = t5;
        t5.next = t6;
      //  t6.next = t7;
     // t7.next = t8;
        LinkedList modifiedList = createOddEven(head);
        while (modifiedList != null){
            System.out.print("\t" + modifiedList.val);
            modifiedList = modifiedList.next;
        }
    }
    public static LinkedList createOddEven(LinkedList head){
        if(head == null && head.next == null)
            return head;
        LinkedList evenNoNodes,evenHead;
        LinkedList oddNoNodes,oddHead;
        evenHead = evenNoNodes = new LinkedList(head.next.val);
        oddHead = oddNoNodes = new LinkedList(head.val);
        head = head.next.next;
        int i= 3;
        while (head != null){
            LinkedList temp = new LinkedList(head.val);
            if(i % 2 == 1) // bit manipulation can be done
            {
                oddNoNodes.next = temp;
                oddNoNodes = oddNoNodes.next;
            }
            else{
                evenNoNodes.next = temp;
                evenNoNodes = evenNoNodes.next;
            }
            i++;
            head = head.next;
        }
        oddNoNodes.next = evenHead;
        return  oddHead;
    }
}
