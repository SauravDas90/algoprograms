package com.hackerrank.test;
// Java program to print BFS traversal from a given source vertex.
// BFS(int s) traverses vertices reachable from s.
import java.io.*;
import java.util.*;

// This class represents a directed graph using adjacency list
// representation
class BFSGeek
{
    private int V; // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists

    // Constructor
    BFSGeek(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    // Function to add an edge into the graph
    void addEdge(int v,int w)
    {
        adj[v-1].add(w);
        adj[w-1].add(v);
    }

    // prints BFS traversal from a given source s
    void BFS(int s)
    {
        // Mark all the vertices as not visited(By default
        // set as false)
        int starting = s;
        boolean visited[] = new boolean[V];
        int dist[] =new int[V];
        int count = 0;
        int temp =0;
        Arrays.fill(dist,0);
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[s-1]=true;    //marks the nth node
        queue.add(s);

        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
           // System.out.print(s+" ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s-1].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n-1])
                {
                    visited[n-1] = true;
                   // if(dist[n-1] == -1)
                   //     temp  =6*count+ 6;
                    if(dist[n-1] > 0 )
                        dist[n-1] +=6;
                    else if((dist[n-1] == 0))
                        dist[n-1] = dist[s-1]+ 6 ;

                   /* else if(dist[n-1] > 0 && temp < dist[n-1])
                        dist[n-1] = temp ;*/
                    queue.add(n);
                }
            }
            count++;
        }

        for(int i =0 ; i <V ; i++) {
            if(i != starting-1 && dist[i]>0)
                System.out.print(dist[i]+" ");
            else if(i != starting-1 && dist[i] == 0)
                System.out.print("-1 " );
        }
    }

    // Driver method to
    public static void main(String args[])
    {

/*
        BFSGeek g = new BFSGeek(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
*/
        int q, n, m, u, v, s;
        Scanner in =  new Scanner(System.in);
        q = in.nextInt();
        for (int i = 0; i < q; i++) {
            n = in.nextInt();           // no of nodes
            m = in.nextInt();           // no of vertices

            BFSGeek g = new BFSGeek(n);

            for (int j = 0; j < m; j++) {
                u = in.nextInt();
                v = in.nextInt();
                g.addEdge(u,v);

            }

            s = in.nextInt();
            g.BFS(s);
            g = null;
            System.out.println();
        }


    }
}
// This code is contributed by Aakash Hasija
