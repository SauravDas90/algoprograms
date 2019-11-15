package com.hackerrank.test.practice;

import java.util.Scanner;

/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
class Node {
    Object data;
    Node next;
}


public class LinkedListInsertion {
    public static void main(String args[])  {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT
         */
        //Scanner
        Scanner in = new Scanner(System.in);
        int sum = 0;
        Node head, traverse, nextEl, reverseNode;
        head = traverse = null;   // when using unitialized object reference in condition, null is to be mentioned
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            if (head == null) {
                head = new Node();
                head.data = in.nextInt();
                head.next = null;
            } else if (head.next == null) {
                head.next = new Node();
                head.next.data = in.nextInt();
                head.next.next = null;
                traverse = head.next;
            } else {
                while (traverse.next != null) {
                    traverse = traverse.next;
                }
                nextEl = new Node();
                nextEl.data = in.nextInt();
                nextEl.next = null;
                traverse.next = nextEl;
            }
        }

        nextEl = head;


        /*while(nextEl!= null){
            sum += nextEl.data;
            if(sum%2 == 0){
                if(reverseNode == null){
                    reverseNode = nextEl;
                }
                else if(reverseNode.next == null){
                    reverseTemp.next = nextEl;
                   // reverseTemp.next.
                }


            }
            else
                sum = 0;

            if(next)
            System.out.print(nextEl.data +"  ");
            nextEl = nextEl.next;
        }*/


    }


    // Write your code here
}

