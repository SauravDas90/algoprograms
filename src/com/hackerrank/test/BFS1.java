package com.hackerrank.test;

import java.util.*;

/**
 * Created by saurav on 9/5/17.
 */
public class BFS1 {


    public static void main(String args[]) {

        int q, n, m, u, v, s;
        Scanner in = new Scanner(System.in);
        LinkedList<Integer> adjacency[];
        q = in.nextInt();               // no of queries

        // Creation of adjacency list

        for (int i = 0; i < q; i++) {
            n = in.nextInt();           // no of nodes
            m = in.nextInt();           // no of vertices
            adjacency = new LinkedList[n];
            for (int j = 0; j < m; j++) {
                u = in.nextInt();
                v = in.nextInt();
                if (adjacency[u - 1] == null)
                    adjacency[u - 1] = new LinkedList<>();      // the nth no linked List adds the value..
                adjacency[u-1].add(v);
            }

            s = in.nextInt();


            // BFS Algorithm actual.

            Queue<Integer> visited = new ArrayDeque<>();           // Queue to keep track of visited nodes and adding nodes


            int k = 0,count = 0, dist[] = new int[n],temp=-1;
            Arrays.fill(dist,-1);
            while(k<n){

                if(count ==0){

                    //   while(k<n){
                   // if(adjacency[s-1] != null){
                    for (int j = 0; j < adjacency[s-1].size() ; j++) {
                        temp = adjacency[s-1].get(j);
                        dist[temp-1] = 6;

                    }
                    visited.addAll(adjacency[s-1]);
                    }
                else {
                    int nextNode = -1;
                    nextNode = visited.size() > 0 ?visited.poll():-1;
                    if(nextNode != -1 && adjacency[nextNode-1] != null){
                        for (int j = 0; j < adjacency[nextNode-1].size() ; j++) {
                            temp = adjacency[nextNode-1].get(j);
                            if(dist[temp-1] == -1)
                                dist[temp-1] = count *6 +6;
                        }

                        visited.addAll(adjacency[nextNode-1]);

                    }
                }

                count ++;
                k++;
            }

            adjacency =  null;
            visited = null ;

            for(i=0;i<n;i++)
                System.out.print(dist[i]+" ");

            System.out.println();

        }
    }
}
