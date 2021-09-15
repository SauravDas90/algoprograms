package com.pepcoding.backtracking.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FindAllPaths {

    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int edges = in.nextInt();

        boolean[] visited = new boolean[nodes];
        LinkedList<NodeWeight>[] graphs = new LinkedList[nodes];

        for(int i = 0 ; i< nodes ; i++){
            graphs[i] = new LinkedList<>();
        }

        for(int i=0 ; i < edges ; i++){
            int u= in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();

            graphs[u-1].add(new NodeWeight(v,w));
            graphs[v-1].add(new NodeWeight(u,w));

        }
        doFindAllPaths(graphs,visited,"1",5,1);
        Arrays.fill(visited,false);
        doFindAllPaths(graphs,visited,"5",1,5);


    }

    public static void doFindAllPaths(LinkedList<NodeWeight>[] graph, boolean[] visited, String psf,int dest, int src){
        if(src == dest){
             System.out.println("visited path is " + psf);
        }

        visited[src-1] = true;
        for(NodeWeight nbr : graph[src-1]){
            if(!visited[nbr.vertex-1]){
                doFindAllPaths(graph, visited, psf + " " + nbr.vertex, dest, nbr.vertex);
            }
        }
        // unmarking the path, so that others can be visited
        visited[src-1] = false;


    }
}

// learnings
// in case of path so far, always begin with the node, and add it to psf -- e.g. src is 1 , so add it to path so far
// the reason for not adding the final element is , since we are already adding the current vertex , in path so far
// hence no need to add it again in if(src == dest) condition