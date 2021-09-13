package com.pepcoding.backtracking.graph;


import java.util.LinkedList;
import java.util.Scanner;


class Node extends NodeWeight {

    Node(){
    }


    Node(int vertex, int edgeweight){
        this.vertex = vertex;
        this.edgeweight = edgeweight;
    }

}
public class DFS {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int edges  = in.nextInt();

        boolean[] visited = new boolean[nodes];
        LinkedList<Node1>[] graph = new LinkedList[nodes];

        for (int i = 0; i < nodes; i++) {
            graph[i] = new LinkedList<Node1>();
        }

        for(int i = 0 ; i < nodes; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();

            graph[u-1].add(new Node1(v,w));
            graph[v-1].add(new Node1(u,w));
        }

        boolean foundNode = doDFS(graph,visited,1,7);
        if(foundNode)
            System.out.println("path found to destination ");
        else
            System.out.println("Unable to find path");

    }



    public static boolean  doDFS(LinkedList<Node1>[] graph, boolean visited[], int src, int dest) {
        if (src == dest)
            return true;
        // marking the node as visited
        visited[src-1] = true;
        for(Node1 nbr: graph[src-1]){
            if(!visited[nbr.vertex-1]){
                boolean foundNode = doDFS(graph,visited,nbr.vertex,dest);
                // this is to stop the DFS and return from the node depth
                if(foundNode)
                    return true;
            }
        }

        return false;
    }


}
