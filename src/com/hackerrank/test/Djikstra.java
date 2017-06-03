package com.hackerrank.test;

import java.util.*;

/**
 * Created by saurav on 13/5/17.
 */
public class Djikstra {

    boolean visited[];
    static LinkedHashMap<Integer,Integer> adjLst[];
    Queue <Integer> nodes ; // this will be a HashMap (Key as the vertices and,value as edge cost)
    static int dist[];

    public Djikstra(int n) {
        this.adjLst = new LinkedHashMap[n];
        this.visited = new boolean[n];
        this.dist = new int[n];
        this.nodes = new LinkedList<>();
        Arrays.fill(dist, 0);
        Arrays.fill(visited,false);
        for(int i=0 ;i<n; i++)
        {
            adjLst[i] = new LinkedHashMap<>();
        }
    }

    // this code is for sorting Map based on the values
    // Very imp piece of code.

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return ( o1.getValue() ).compareTo( o2.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }

    // for list of nodes we need to use priority queue based on priority heap
    public static void main(String args[]){

        int q, n, m, u, v, s ,cost;
        Scanner in =  new Scanner(System.in);
        q = in.nextInt();
        for (int i = 0; i < q; i++) {
            n = in.nextInt();           // no of nodes
            m = in.nextInt();           // no of vertices

            Djikstra g = new Djikstra(n);

            for (int j = 0; j < m; j++) {
                u = in.nextInt();
                v = in.nextInt();
                cost = in.nextInt();
                g.addEdge(u,v,cost);

            }

            s = in.nextInt();
                adjLst[s-1] = (LinkedHashMap)sortByValue(adjLst[s-1]);
            g.BfsDjikstra(s);
            for(int k =0 ; k <n;k++) {
                if(k != s-1)
                System.out.print(dist[k] + " ");
            }
        }


    }

    // Function to add an edge into the graph
    void addEdge(int v,int w,int cost)
    {
        if(adjLst[v-1].get(w)!= null){
            if (adjLst[v-1].get(w) == 0 || adjLst[v-1].get(w) > cost)                                   // always remember that we cant insert duplicate values in Map(it will be overwritten)
                adjLst[v-1].put(w,cost);
            }
        else
            adjLst[v-1].put(w,cost);
        // Read SortedSet & LinkedHashMap,HashMap and sorting stuffs
        if(adjLst[w-1].get(v) != null) {
            if (adjLst[w - 1].get(v) == 0 || adjLst[w - 1].get(v) > cost)                                   // always remember that we cant insert duplicate values in Map(it will be overwritten)
                adjLst[w - 1].put(v, cost);
        }
        else
            adjLst[w - 1].put(v, cost);
    }

    void BfsDjikstra(int start)
    {
        int s = start,nextNode, cost ,count =0;
        visited[s-1] = true;
        nodes.add(s);

        while(!nodes.isEmpty())
        {
            s = nodes.poll();
            adjLst[s-1] = (LinkedHashMap)sortByValue(adjLst[s-1]);
            cost = 0;// the next element for which you need the adjacent elements

            Iterator<Integer> itr = adjLst[s-1].keySet().iterator();             // for(Priority queue use the iterator, no get and list
            while(itr.hasNext()){
                nextNode= itr.next();
                cost = adjLst[s-1].get(nextNode);
              /*  if(!visited[nextNode-1]) {
                    if(dist[s - 1] + cost < dist[nextNode-1] || dist[nextNode-1] == 0)
                        dist[nextNode-1] = dist[s - 1] + cost;                        // remembers the previous distance
                    visited[nextNode-1] = true;                                // marks it as visited
                                                    // adding to bfs
                }
                else if(dist[s - 1] + cost < dist[nextNode-1])
                    dist[nextNode-1] = dist[s - 1] + cost;

                if(count == 0)
                    nodes.add(nextNode);
                count++;
*/
                if(dist[s - 1] + cost < dist[nextNode-1] || dist[nextNode-1] == 0) {
                    dist[nextNode - 1] = dist[s - 1] + cost;
                    nodes.add(nextNode);
                   // visited[nextNode-1] = true;
                }
                if(!visited[nextNode-1])
                    visited[nextNode-1] = true;



            }
        }
    }
}
