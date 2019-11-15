package com.hackerrank.test.practice;
import java.util.*;
import java.io.*;


public class ReverseLinkedList{

    static class Node{
        int data;
        Node next;
        Node(int data,Node next){
            this.data = data;
            this.next = next;
        }
    }

    static Node reverseHead= null;
    public static void main(String[] args)  {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
          final Scanner scanner = new Scanner(System.in);

        Node head,temp,traverse;
        head = traverse = null;
        int noOfElements = scanner.nextInt();

        for(int i=0;i<noOfElements;i++){
        int no = scanner.nextInt();
         if(null == head){
             head = new Node(no,null);
         }
         else if(null == head.next){
             temp = new Node(no,null);
              head.next = traverse = temp;
         }
         else{
             while(null != traverse.next){
                 traverse = traverse.next;
             }
             temp = new Node(no,null);
                traverse.next = temp;

         }


        }
        reverse(head);

        temp = reverseHead;
        while(temp != null){
            System.out.println(temp.data);
            temp = temp.next;
            //temp = temp.nex
        }
        scanner.close();
    }

    static Node reverse(Node head){

        // first condition check if the input is null
        // second condition checks for termination condition
        if(head == null || head.next == null)
            return head;
        Node tail  = reverse(head.next);
       /* traverse = tail
        while(traverse != null){
            traverse = traverse.next;
        }*/
        if(reverseHead == null)
            reverseHead = tail;
        tail.next = head;
        head.next = null;
        return head;

    }


}


 /*if(head == null || head.next == null)
         return head;

         SinglyLinkedListNode tail = reverse(head.next);
         SinglyLinkedListNode traverse = tail;
         while(traverse != null)
         traverse = traverse.next;
         traverse.next = head;
         head.next = null;
         return tail;*/