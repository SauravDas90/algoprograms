//package com.leetcode.practice;
//
//public class RemoveDuplicates {
//    public static void main(String[] args) {
//        int[] arr = {1,1,1,2,2,3,3};/*
//        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
//        LinkedList<Integer> linkedList = new LinkedList<>(list);*/ // If You implement LinkedList, then it a list and not a node, you need to use ListNode
//        LinkedList<N> head = new LinkedList<N>(2);
//        LinkedList<N> t2 = new LinkedList<N>(1);
//        LinkedList<N> t3 = new LinkedList<N>(1);
//        LinkedList<N> t4 = new LinkedList<N>(2);
//        LinkedList<N> t5 = new LinkedList<N>(2);
//        LinkedList<N> t6 = new LinkedList<N>(2);
//        LinkedList<N> t7 = new LinkedList<N>(2);
//      //  LinkedList t8 = new LinkedList(3);
//        head.next = t2;
//        t2.next = t3;
//        t3.next = t4;
//        t4.next = t5;
//       /* t5.next = t6;
//        t6.next = t7;
//       */ //t7.next = t8;
//
//       LinkedList<N> temp = removeDuplicates(head);
//       int i = 0;
//       while (temp != null) {
//
//           System.out.println("temp.val = "+i+" "+ temp.val);
//           i++;
//           temp = temp.next;
//       }
//    }
//
//    public static LinkedList<N> removeDuplicates(LinkedList<N> llist){
//        if(llist == null || llist.next == null)
//            return llist;
//
//       LinkedList<N> head,prev,next;
//       head = prev= next = llist;
//
//       while(next.next != null){
//           if(prev.val == next.val)
//               next = next.next;
//           else{
//               prev.next = next;
//               prev = prev.next;
//           }
//       }
//        if(prev.val == next.val)
//            prev.next = null;  // already exist handles 2->3-3
//        else
//            prev.next = next;
//       return head;
//    }
//}
