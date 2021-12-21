package com.pepcoding.backtracking.graph;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class TopologicalSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int edges = in.nextInt();

        boolean visited[] = new boolean[nodes];
        LinkedList<NodeWeight>[] graph = new LinkedList[nodes];
        Stack<Integer> sortedNode = new Stack<>();

        for(int i =0; i< nodes; i++){
            graph[i] = new LinkedList<>();
        }

        for(int i =0; i< edges; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            // directed only graph
            graph[u-1].add(new NodeWeight(v,w));
        }


        // loop through the nodes
        for (int i = 0; i < nodes; i++) {
            if(!visited[i]) {
                // only visited the nodes, if not visited
                // making it only i+1, as array indexing
                doTopologicalSort(graph, visited, i + 1, sortedNode);
            }
        }
        while(! sortedNode.isEmpty()){
            System.out.print(" node = " + sortedNode.pop() + "\t");
        }
    }

    public static void doTopologicalSort(LinkedList<NodeWeight>[] graph, boolean visited[], int src, Stack<Integer> sortedNode ){

        // checking if the outdegree, like how many nodes are dependent
        int outdegree = graph[src-1].size();
        if(outdegree == 0 ){
            // adding the nodes with no dependency
            if (!visited[src-1]) {
                sortedNode.push(src);
                // marking the node as visited
                visited[src-1] = true;
            }
            // as it has no outdegree so return back
            return;
        }

        for(NodeWeight nbr: graph[src-1]){
            if (!visited[nbr.vertex-1]) {
                doTopologicalSort(graph,visited, nbr.vertex, sortedNode);
            }
        }
        // marking the node as visited
        visited[src-1] = true;
        sortedNode.push(src);
    }
}
// simplifying the logic

// 1. create a directed graph i.e. dont add element on other places
// 2. for a node first check if the visited node has any outdegree
    //, if outdegree = 0 then just mark it as visited and it to the stack
// 3. if the element is having on zero outdegree, first visit all the elements ,
 //   a. notice that in this case, we dont mark the elements visited as soon as it called
// we reach till th end of the leaf and then mark it as visited
