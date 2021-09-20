package com.pepcoding.backtracking.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class ZeroOneBFS {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int edges = in.nextInt();
        int vertices = in.nextInt();

        LinkedList<NodeWeight> graph[] = new LinkedList[edges];
        boolean visited[] = new boolean[edges];

        for (int i = 0; i < edges; i++) {
            graph[i] = new LinkedList<>();
        }

        for(int i = 0 ; i < vertices ; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            //int w = in.nextInt();

            graph[u-1].add(new NodeWeight(v,0));
            graph[v-1].add(new NodeWeight(u,1));

        }
        do01Bfs(graph,visited,1);
    }

    public static void do01Bfs(LinkedList<NodeWeight> graphs[], boolean[] visited, int startingnode){
        Queue<NodeWeight> priorityQueue = new PriorityQueue<>();

        priorityQueue.offer(new NodeWeight(startingnode,0));

        while (!priorityQueue.isEmpty()){

            NodeWeight currNode = priorityQueue.poll();

            if(visited[currNode.vertex -1]){
                continue;
            }

            visited[currNode.vertex-1] =true;

            System.out.println("currNode = " + currNode);

            for(NodeWeight nbr: graphs[currNode.vertex-1]){
                if(!visited[nbr.vertex-1]){
                    priorityQueue.offer(nbr);
                }
            }
        }

    }

}
