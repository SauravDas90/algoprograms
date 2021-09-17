package com.pepcoding.backtracking.graph;


import java.util.*;

class DjikstraComparator implements Comparator<NodeWeight>{

    @Override
    public int compare(NodeWeight o1, NodeWeight o2) {
        return o1.edgeweight- o2.edgeweight;
    }
}

public class Djikstra {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int edges = in.nextInt();

        boolean[] visited = new boolean[nodes];
        LinkedList<NodeWeight>[] directedGraph = new LinkedList[nodes];

        for (int i = 0; i < nodes; i++) {
            directedGraph[i] = new LinkedList<>();
        }

        for (int i = 0; i < edges; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();

            directedGraph[u-1].add(new NodeWeight(v,w));
        }

        doDjikstra(directedGraph,visited);

    }

    public static void doDjikstra(LinkedList<NodeWeight>[] directedGraph, boolean[] visited){
        Queue<NodeWeight> priorityQueue  = new  PriorityQueue<>(new DjikstraComparator());

        priorityQueue.offer(new NodeWeight(1,0));
        while(! priorityQueue.isEmpty()){

            // remove the current Node
            NodeWeight currnode = priorityQueue.poll();

            // mark the node visited
            if(visited[currnode.vertex-1])
                continue;
            visited[currnode.vertex-1] = true;

            // work is printing
            System.out.println("currnode with shortest distance from source = " + currnode);
            // add
            for(NodeWeight nbr: directedGraph[currnode.vertex-1]){
                if(!visited[nbr.vertex-1]){
                    int totalweight = nbr.edgeweight+ currnode.edgeweight;
                    // creating new neighbours with weights
                    priorityQueue.offer(new NodeWeight(nbr.vertex, totalweight));
                }
            }

        }


    }
}
