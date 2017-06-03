package com.hackerrank.test;

/**
 * Created by saurav on 16/5/17.
 */



import java.util.*;

/**
 * Created by saurav on 15/5/17.
 */
class Nodes implements Comparable<Nodes> {


    int nextNode;
    int cost ;

    public Nodes(int node,int cost)
    {
        this.nextNode = node ;
        this.cost = cost;
    }

    @Override
    public int compareTo(Nodes o2) {
        return (this.cost < o2.cost? -1:1);
    }
}



public class PrimALgo {

    static LinkedList<Nodes>[] adjList;
    static PriorityQueue<Nodes> priorityQueue;
    static int dist[];
    boolean visited[];
    static int firstn;
    public PrimALgo(int n) {

        adjList = new LinkedList[n];
        dist = new int[n];
        visited = new boolean[n];
       // Arrays.fill(dist,Integer.MAX_VALUE);
        Arrays.fill(dist,0);
        Arrays.fill(visited,false);
        for(int i =0;i< n;i++)
            adjList[i] = new LinkedList<>();

    }

    public static void main(String args[]) {

        int q, n, m, u, v, s, cost;
        Scanner in = new Scanner(System.in);
            n = in.nextInt();           // no of nodes
            m = in.nextInt();           // no of vertices

            PrimALgo g = new PrimALgo(n);

            for (int j = 0; j < m; j++) {
                u = in.nextInt();
                v = in.nextInt();
                cost = in.nextInt();
                g.addEdge(u, v, cost);

            }

            firstn =s = in.nextInt();
            // adjLst[s - 1] = (LinkedHashMap) sortByValue(adjLst[s - 1]);
            g.prim();
            int sum =0 ;
            for (int k = 0; k < n; k++) {
                if(k != firstn){
                   // dist[k] += Integer.MAX_VALUE;
                   // System.out.print(dist[k]+" ");
                    sum += dist[k];
                }
            }

            System.out.println(sum);

    }


    public void addEdge(int v,int w, int cost){


        adjList[v - 1].add(new Nodes(w,cost));
        adjList[w - 1].add(new Nodes(v,cost));

     /*   if(adjList[v-1].get(next.nextNode) != null) {
            if ( adjList[v-1].get(next) > cost)
                adjList[v - 1].put(w, cost);
        }
*/
    }

    public void prim(){

        Nodes firstnode = new Nodes(firstn,0);
        firstn = firstnode.nextNode-1;
        Nodes currN;
        priorityQueue = new PriorityQueue<>();
        priorityQueue.add(firstnode);
        int temp;
        while(!priorityQueue.isEmpty()){

            Nodes s = priorityQueue.poll();
            System.out.println(" the starting node is "+ s.nextNode + "\n");
            Iterator<Nodes> itr = adjList[s.nextNode-1].iterator();
            visited[s.nextNode-1] = true;
            //dist[s.nextNode-1] = s.cost;
            //currN = s.nextNode;
            while(itr.hasNext()){

                currN = itr.next();
             /*   if((dist[currN.nextNode-1] > dist[s.nextNode-1]+ currN.cost) || dist[currN.nextNode-1] == 0){

                    dist[currN.nextNode-1] = dist[s.nextNode-1]+ currN.cost;
                //    visited[currN.nextNode-1] = true;
                }
*/

                if(!visited[currN.nextNode-1] && ((dist[currN.nextNode-1] == 0 || dist[currN.nextNode-1]> currN.cost)))
                    dist[currN.nextNode-1] = currN.cost;

                if(!visited[currN.nextNode-1])
                    priorityQueue.add(new Nodes(currN.nextNode,dist[currN.nextNode-1]));


            }

            System.out.println("\n");
            System.out.println("----------------------------------------------------------");
            for(int i=0;i<dist.length; i++)
            {
                System.out.print("\t"+ dist[i] + "\t");
            }
            System.out.println("\n");

            for(Nodes s2:priorityQueue) {
                System.out.print("\t" + s2.nextNode + ",");
                System.out.print(s2.cost + "\t");
            }

        }

    }

}
