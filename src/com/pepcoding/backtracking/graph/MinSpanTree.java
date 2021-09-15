package com.pepcoding.backtracking.graph;

import java.util.*;

class MinComparator implements Comparator<NodeWeight>{

    @Override
    public int compare(NodeWeight o1, NodeWeight o2) {
        return o1.edgeweight - o2.edgeweight;
    }
}

public class MinSpanTree {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int nodes = in.nextInt();
        int edges = in.nextInt();

        LinkedList<NodeWeight>[] graph = new LinkedList[nodes];
        boolean[] visited = new boolean[nodes];
        for (int i = 0; i < nodes; i++) {
            graph[i] = new LinkedList<>();
        }

        for(int i=0; i < nodes;i++){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            // Prim's algo works only on Directed graphs
            graph[u-1].add(new NodeWeight(v,w));
            graph[v-1].add(new NodeWeight(u,w));
        }

            doGenerateMST(graph,visited,1);
    }

    public static void doGenerateMST(LinkedList<NodeWeight>[] graph, boolean visited[],int src){
        Queue<NodeWeight> nbr = new PriorityQueue<>();

        // adding the first node and the dummy weight
        nbr.offer(new NodeWeight(1,-1));

        while(! nbr.isEmpty()){

            // remove
            NodeWeight nxt = nbr.poll();

            // mark it
            if (visited[nxt.vertex-1]) {
                continue;
            }

            visited[nxt.vertex-1] =true;
            //work
            System.out.println("nxt neighbout with weight = " + nxt);

            // add star
            for (NodeWeight shortNbr: graph[nxt.vertex-1]){
                if(!visited[shortNbr.vertex-1]){
                    nbr.offer(shortNbr);
                }
            }
        }
    }
}
