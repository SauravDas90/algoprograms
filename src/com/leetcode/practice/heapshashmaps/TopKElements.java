package com.leetcode.practice.heapshashmaps;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class TopKElements {
    public static void main(String[] args) {
        int arr[] = IntStream.range(0,10).toArray();
        topKelements(4,arr);
    }

    public static void topKelements( int k, int arr[] ){
        Queue<Integer> minHeap = new PriorityQueue<>(k);

        for (int i = 0; i < arr.length; i++) {

            minHeap.offer(arr[i]);
            if (k < minHeap.size()){
                minHeap.poll();
            }
        }
        System.out.println("... Output of the top K elements in reverse order...");
        while (minHeap.size() > 0){
            System.out.print("\t  " + minHeap.poll());
        }
    }
}
