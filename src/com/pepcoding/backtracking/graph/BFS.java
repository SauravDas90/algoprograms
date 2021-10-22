package com.pepcoding.backtracking.graph;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int edges = in.nextInt();

        boolean visited[] = new boolean[nodes];
        LinkedList<NodeWeight> graph[] = new LinkedList[nodes];
        for (int i = 0; i < nodes; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < edges; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();

            graph[u-1].add(new NodeWeight(v,w));
            graph[v-1].add(new NodeWeight(u,w));
        }
        doBFS(graph,visited,1);
    }
    
    public static void doBFS(LinkedList<NodeWeight> graph[], boolean visited[],int src){

        Queue<NodeWeight> nbr = new ArrayDeque<>();
        NodeWeight start = new NodeWeight(src,0); // dummy one
        
        nbr.offer(start);
        while(! nbr.isEmpty()){
            
            NodeWeight currentNode = nbr.poll();
            int ver =currentNode.vertex -1;

            // in BFS we dont visit the single path
            // we use the breadth, due to which single element can be added to queue
            // by multiple visiting
            if(visited[ver])
                continue;

            visited[ver] = true;

            System.out.println("current node = " + currentNode.vertex);
            for(NodeWeight nebr: graph[ver]){
                int neigVer = nebr.vertex-1;
                if(!visited[neigVer]){
                    nbr.offer(nebr);
                }
            }
        }
    }
}
