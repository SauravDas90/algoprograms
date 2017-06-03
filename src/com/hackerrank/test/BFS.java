package com.hackerrank.test;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by saurav on 9/5/17.
 */
public class BFS {

    public static void main(String args[]){

        int q,n,m,u,v,s;
        Scanner in = new Scanner(System.in);
        LinkedList<Integer> adjacency [];
        q = in.nextInt();               // no of queries

        for(int i=0;i<q;i++){
            n = in.nextInt();           // no of nodes
            m = in.nextInt();           // no of vertices
            adjacency = new LinkedList[n];
            for(int j =0 ;j<m;j++){
                u = in.nextInt();
                v = in.nextInt();
                if(adjacency[u-1] == null)
                adjacency[u-1] = new LinkedList<>();      // the nth no linked List adds the value..
                adjacency[u-1].add(v);
            }

            s= in.nextInt();

            int k =0,dist[] = new int[n],temp=-1;
         //   while(k<n){

                for (int j = 0; j < adjacency[s-1].size() ; j++) {
                    temp = adjacency[s-1].get(j);
                    dist[temp-1] = 6;
                    k++;
                }
           // }

            for (int j = 0; j < dist.length; j++) {
                if(j+1 != s)
                    if(dist[j] !=0)
                    System.out.print(dist[j]+" ");
                    else
                       System.out.print("-1 ");
            }

            System.out.println();

        }
    }

}
