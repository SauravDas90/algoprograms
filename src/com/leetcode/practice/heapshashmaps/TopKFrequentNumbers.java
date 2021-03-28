package com.leetcode.practice.heapshashmaps;

import java.util.*;
import java.util.stream.IntStream;

class Pair implements  Comparable<Pair>{
    int elem,count;


    public Pair(int elem, int count) {
        this.elem = elem;
        this.count = count;
    }

    public int getElem() {
        return elem;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(Pair o) {
        return this.count-o.count;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "elem=" + elem +
                ", count=" + count +
                '}';
    }
}

public class TopKFrequentNumbers {
    public static void main(String args[]){
        //int arr[] = {1,2,3,1,2,2,2,3,3,4,5,4,4,4,6,1};
        int arr[] = {2,2,2,2,1,3,1,3,3,4,5,4,4,4,6,1};
        kFrequentNumbers(arr,4);


    }

    public static void kFrequentNumbers(int[] arr, int k){
        Map<Integer,Integer> count= new HashMap<>();

        Queue<Pair> topKElements = new PriorityQueue<>();

        for (int i:arr){
           if(!count.containsKey(i)){
               count.put(i,1);
           }
           else count.put(i, count.get(i)+1);
        }

        Map<Pair,Integer> map = new TreeMap<>();
        for(int x:count.keySet()){
            Pair p = new Pair(x,count.get(x));
            topKElements.offer(p);
            map.put(p,p.elem);

            if(topKElements.size() > k){
                topKElements.poll();
            }
        }


        System.out.println("map.toString() = " + map.toString());


     /*   System.out.println("Top elements are ... ");
        for (Pair p:topKElements){
            System.out.println("element  = " + p.elem + " count  = " + p.count);
        }*/

    }
}
