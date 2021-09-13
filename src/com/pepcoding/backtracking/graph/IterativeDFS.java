package com.pepcoding.backtracking.graph;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;



public class IterativeDFS {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int edges = in.nextInt();

        LinkedList<NodeWeight> graph[] = new LinkedList[nodes];
        boolean visited[] = new boolean[nodes];

        for (int i = 0; i < nodes; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < nodes; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();

            graph[u-1].add(new NodeWeight(v,w));
            graph[v-1].add(new NodeWeight(u,w));
        }
        doIterativeDFS(graph,visited,1,7);

    }

    public static void doIterativeDFS(LinkedList<NodeWeight> graph[], boolean visited[], int src, int dest){

        Stack<NodeWeight> neighbours = new Stack<>();

        // creating a Dummy Node
        NodeWeight startNode1 = new NodeWeight(src,0);

        neighbours.push(startNode1);

        while (!neighbours.isEmpty()){
            NodeWeight currentNode1 = neighbours.pop();

            if(currentNode1.vertex == dest){
                System.out.println(" Found a path to destination");
                return;
            }
            //
            visited[currentNode1.vertex-1] = true;

            for (NodeWeight nbr : graph[currentNode1.vertex-1]){
                if(!visited[nbr.vertex-1]){
                    neighbours.push(nbr);
                }
            }
        }

        System.out.println("Path not found to the destination");
    }
}
