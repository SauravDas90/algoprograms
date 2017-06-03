package com.codeforces.div2;  // never enter package in the solution

import java.util.*;

/**
 * Created by saurav on 25/5/17.
 */
public class Problem580C {

    int distcount =0 ;
    static LinkedHashMap <Integer,Integer> colorMap = new LinkedHashMap<>(); // Hashmap for using the values of color plus
    static Stack<LinkedHashMap<Integer,Integer>> dfsTraverse = new Stack<>();

    static class Nodes{

        static int color[];
        static boolean visited[];
        static ArrayList<Integer> tree[];
        static int restCount;
    }


    public static void main(String args[])    {

        int n,m,u,v;   // no of vertices and consecutive vertice//

        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        Problem580C prob = new Problem580C();

        // initializing


        Nodes.tree = new ArrayList[n];
        Nodes.color = new int[n];
        Nodes.visited = new boolean[n];

        for(int j = 0 ; j < n ; j++ ) {
            Nodes.color[j] = in.nextInt();
            Nodes.tree[j] = new ArrayList<>();

        }

        for(int i = 0;i<n-1;i++){

            u = in.nextInt();
            v =in.nextInt();

            Nodes.tree[u-1].add(v);
            Nodes.tree[v-1].add(u);

/*

            if(u<v){
                if(Nodes.tree [ u-1] == null)
                    Nodes.tree[u-1] = new ArrayList<>();

                Nodes.tree[u-1].add(v);               // adding only directed edges

            }
            else
            {
                if(Nodes.tree [ v-1] == null)
                    Nodes.tree[v-1] = new ArrayList<>();
                                                            // adding only directed edges
                Nodes.tree[v-1].add(u);
            }



            for(i=1;i<=n;i++) {
                if(!Nodes.visited[i-1])
                    prob.restaurantReachable(i,m);
            }

            System.out.println(Nodes.restCount);*/
        }





        int top = 0,previous=-1;
        colorMap.put(top,Nodes.color[top]);   // you cant add the colorMap.put as it return the previous key , so add it and then pass
        dfsTraverse.push(colorMap);   // we are passing it repeatedly so keep check
       Nodes.visited[top] = true;

        dfs(top,Nodes.color[top],m);
        System.out.println(Nodes.restCount);

    }

   /* void restaurantReachable(int start,int m){

        Stack<Integer> dfsTraverse = new Stack<Integer>();

        int top = start,color=0;
        dfsTraverse.push(top);
        Nodes.visited[top-1] = true;

        while(!dfsTraverse.empty())
        {
            int nextNode = Nodes.tree[top-1].iterator().next();

        }


    }

*/

    static void dfs(int current,int distcolor, int m){


        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = Nodes.tree[current].listIterator();
        //Nodes.restCount =
        while (i.hasNext())
        {
            int nxt = i.next();
         /*   if (!Nodes.visited[nxt-1] && distcolor<= m && Nodes.tree[nxt-1].size() == 1) {
                Nodes.restCount++;          // since its a leaf , so increment
                Nodes.visited[nxt-1] = true;  // mark the node as visited
            }

           else if (!Nodes.visited[nxt-1] && distcolor<= m && Nodes.tree[nxt-1].size() > 1) {
                dfsTraverse.push(nxt-1);
                dfs(nxt - 1,(distcolor+=(Nodes.color[nxt-1])),m);

            }
            else if(Nodes.visited[nxt-1] ||distcolor > m ){
              //  dfsTraverse.pop();
            }*/

            if(!Nodes.visited[nxt-1]){
                if(Nodes.color[nxt-1] == 0)
                    distcolor=0;
                else if( (distcolor+ Nodes.color[nxt-1]) > m)  // since greater than minimum no of cats,path not possible
                    continue;

                    Nodes.visited[nxt-1] = true ; // mark it as visited
                    if(Nodes.tree[nxt-1].size() == 1){  // leaf node , so add
                        Nodes.restCount++;
                    }
                    else{
                        colorMap.put(nxt-1,distcolor + Nodes.color[nxt-1]);
                    dfsTraverse.push(colorMap);
                    dfs(nxt - 1,(distcolor+ Nodes.color[nxt-1]),m);
                    }

                }
            distcolor = dfsTraverse.peek().get(current);   // check the distcolor of previous.

            }


        Nodes.visited[current] =  true;
        dfsTraverse.pop();



            }

        }





