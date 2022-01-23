package com.pepcoding.backtracking.graph;

import java.util.*;

public class Bipartite {

    public static void main(String args[]){
        List<List<Integer>> graph = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        graph.add(list1);
        //
        list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list1.add(5);
        graph.add(list1);
        //
        list1 = new ArrayList<>();
        list1.add(2);
        list1.add(4);
        graph.add(list1);
        list1 = new ArrayList<>();
        list1.add(5);
        list1.add(3);
        graph.add(list1);
        // adding even cycle
        list1 =  new ArrayList<>();
        list1.add(2);
        list1.add(4);
        graph.add(list1);

        System.out.println("is bipartitte = " + isBipartite(graph));
    }

    public static boolean isBipartite(List<List<Integer>> graph){

        Queue<Integer> queue = new ArrayDeque<>();
        boolean visited[] = new boolean[graph.size()];
        int[] color = new int[graph.size()];
        // putting the first element , marking it visted and giving color
        queue.offer(1);
        color[0] = 1;

        while(! queue.isEmpty()){

            int curElem = queue.poll();
            visited[curElem-1] = true;
            for(int i: graph.get(curElem-1)){
                if(!visited[i-1]){
                    // assigning different color
                    color[i-1] = 1 - color[curElem-1];
                    queue.offer(i);
                }
                else{
                    // check if the neighbouring element has same color
                    if(color[i-1] == color[curElem-1])
                        return false;
                }
            }
        }
        // after consuming all the element if
        return true;

    }
}
