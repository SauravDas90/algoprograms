package com.hackerrank.test;

import java.util.*;

class Node implements Comparable<Node>{
    int val, cost;
    Node(int val, int cost){
        this.val = val; this.cost = cost;
    }

    public int compareTo(Node x){
        return Integer.compare(this.cost, x.cost);
    }
}

public class SolutionDFS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt(), m = sc.nextInt();
            ArrayList<ArrayList<Node>> adj = new ArrayList<ArrayList<Node>>(n+1);
            for(int i=0; i<n+1; i++)adj.add(new ArrayList<Node>(n+1));

            while(m-- > 0){
                int x = sc.nextInt(), y = sc.nextInt(), cost = sc.nextInt();
                adj.get(x).add(new Node(y, cost));
                adj.get(y).add(new Node(x, cost));
            }
            int s = sc.nextInt();
            djikstra(s, adj, n);
        }
    }

    static void djikstra(int s, ArrayList<ArrayList<Node>> adj, int n){
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(s, 0));
        while(pq.size() > 0){
            Node curr = pq.peek(); pq.remove();
            int currN = curr.val;
            Iterator<Node> it = adj.get(currN).iterator();
            while(it.hasNext()){
                Node temp = it.next();
                if(dist[temp.val] > dist[currN] + temp.cost){
                    pq.add(new Node(temp.val, dist[currN]+temp.cost));
                    dist[temp.val] = dist[currN] + temp.cost;
                }
            }
        }

        for(int i=1; i<dist.length; i++){
            if(i!=s){
                System.out.print(((dist[i] == Integer.MAX_VALUE)?-1:dist[i]) + " ");
            }
        }
        System.out.println();
    }
}