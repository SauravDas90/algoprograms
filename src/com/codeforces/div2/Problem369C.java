package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by saurav on 29/5/17.
 */


public class Problem369C {

    static class Nodes
    {

        int edgeNo;
        int roadCost;

        public Nodes(int edgeNo, int roadCost) {
            this.edgeNo = edgeNo;
            this.roadCost = roadCost;
        }

    }

    static ArrayList<ArrayList<Nodes>> tree ;
    static ArrayList<Integer> subset;
    static int n;
    static int u,v,t;
    static boolean visited[];
    public static void main(String args[]) throws IOException {

       /* Scanner in = new Scanner(System.in);
        n = in.nextInt();
*/
        BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(sc.readLine());
       // String s=sc.readLine();

        tree = new ArrayList(n+1);
        visited = new boolean[n];


        for(int i =0; i< n; i++)
            tree.add(new ArrayList<Nodes>());

        for(int i=0;i<n-1;i++){

            String  s=sc.readLine();
            String[] arr=s.split(" ");
            u=Integer.parseInt(arr[0]);
            v=Integer.parseInt(arr[1]);
            t=Integer.parseInt(arr[2]);
            /*u = in.nextInt();
            v =in.nextInt();
            t = in.nextInt();
          */ tree.get(u-1).add(new Nodes(v,t));
            tree.get(v-1).add(new Nodes(u,t));
        }

      //  Collections.sort(tree);
        subset = new ArrayList<>();
       // subset.add(0);    // Initial 0 is added , 1 element is for being replacing

        visited[0] = true;
        DFStransverse(0,1);
        ArrayList<Integer> subsetfinal = new ArrayList<>();
        System.out.println(subset.size());
        for(int i=0;i<subset.size();i++) {

            System.out.print(subset.get(i)+" ");
        }

    }

    static int DFStransverse(int edge,int currRepair){

        Iterator<Nodes> itr = tree.get(edge).listIterator();
        int leafNodeCountr = 0;
        while(itr.hasNext()){
           // int count =0;
            Nodes n = itr.next();
            if(!visited[n.edgeNo-1]) {
               visited[n.edgeNo-1] = true;
                if(n.roadCost == 2){
                    leafNodeCountr++;
                    //subset.set(subset.size()-1,n.edgeNo);
                    if(tree.get(n.edgeNo-1).size() == 1){

                       subset.add(n.edgeNo);

                       // count++; //just to check if a leaf node with cost 2 has been encountered or not
                    }

                }
                /*if(count > 0) {
                    subset.add(0);
                    count = 0;
                }*/
                leafNodeCountr+=DFStransverse(n.edgeNo-1,n.roadCost);
            }
        }
        if(leafNodeCountr == 0 && currRepair == 2 && !(tree.get(edge).size()==1))
            subset.add(edge+1);

        return leafNodeCountr;
        //visited[edge] = true;
    }

}
