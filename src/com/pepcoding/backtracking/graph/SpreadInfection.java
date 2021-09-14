package com.pepcoding.backtracking.graph;


import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SpreadInfection {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int edges = in.nextInt();

        LinkedList<NodeWeight> graph[] = new LinkedList[nodes];
        boolean visited[] = new boolean[nodes];

        for (int i = 0; i < nodes; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < edges; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();

            graph[v-1].add(new NodeWeight(u,w));
            graph[u-1].add(new NodeWeight(v,w));

        }

        spreadInfection(graph,visited,1,3);

    }

    public static void spreadInfection(LinkedList<NodeWeight> graph[], boolean visited[], int src, int t){

        Queue<NodeWeight> nbr = new ArrayDeque<>();
        NodeWeight start = new NodeWeight(src,0);
        nbr.offer(start);

        // initial value is -1, as want to start with 0
        int count = -1;

        while(! nbr.isEmpty())
        {
            // remove
            NodeWeight currentNode = nbr.poll();
            // mark star
            int ver = currentNode.vertex -1;

            if(visited[ver])
                continue;

            visited[ver] = true;

            // work
            System.out.println("infected vertex = " + currentNode.vertex);
            count++;

            if(count > t)
                return;

            // add star
            for(NodeWeight next: graph[ver]){
                int nextVer = next.vertex -1;
                if(! visited[nextVer])
                    nbr.offer(next);
            }
        }
    }
}
