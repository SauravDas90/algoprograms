package com.hackerrank.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by saurav on 13/5/17.
 */
public class DFS {

   private LinkedList<Integer> adjLst[] ;
    private Stack<Integer> nodes;
    private static boolean visited[];

    public DFS(int vertex){

        adjLst = new LinkedList[vertex];
        visited = new boolean[vertex];
        nodes = new Stack<>();
        Arrays.fill(visited,false);
        for(int i=0;i<vertex;i++)
            adjLst[i] = new LinkedList<>();
    }

    public static void main(String args[]){

        int u,w,start ;
        Scanner in = new Scanner(System.in);
        int vertex = in.nextInt();
        int edges = in.nextInt();
        DFS grapDfs =new DFS(vertex);
        for(int i=0;i<edges;i++)
        {
            u = in.nextInt();
            w = in.nextInt();
            grapDfs.addEdge(u,w);
        }

        //start = in.nextInt();

        /*for(int i=1;i<=vertex;i++) {
            if(!visited[i-1])
                grapDfs.DFSsort(i);
        }
*/
    }

    public void addEdge(int u,int w){

        adjLst[u-1].add(w);
        //  adjLst[w-1].add(u);  // considering it to be Diagraph

    }


    public void DFSsort(int start){

        /*int top = start;
        //nodes.push(top);
        visited[top-1] = true;

       *//* while(!nodes.empty())
        {
       *//*
        for(int k = 0; k < adjLst[top-1].size() ; k++){
            top = adjLst[top-1].get(k);
            if(!visited[top-1]) {
                nodes.push(top);
                visited[top-1] =true;
            }
        }

        nodes.push(start);
        while(!nodes.empty())
            System.out.print(nodes.pop()+" ");*/

/*

        }


*/

    int top = start = 1;
    int elem = 0;
    Stack<Integer> nodes = new Stack<>();
    nodes.push(start);

    while(!nodes.empty()){
        for(int i=0;i<adjLst[top-1].size();i++){
            elem = adjLst[top-1].get(i);
            if(!visited[elem])
            nodes.push(elem);
        }
        visited[top-1] = true;
        top = nodes.pop();
        //System.out.println("top is ", top);

    }




    }
}
