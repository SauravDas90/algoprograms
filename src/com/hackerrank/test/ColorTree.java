package com.hackerrank.test;


import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * Created by saurav on 22/5/17.
 */



public class ColorTree {
/*

    int val;
    int col;
    TreeNode leftchild;
    TreeNode rightchild;

    public TreeNode(int val, int col ){
        this.val = val;
        this.col = col;
    }
}

public class ColorTree {


    static TreeNode tree[];

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        int vert,edge,quer;

        vert = in.nextInt();
        edge = in.nextInt();
        quer = in.nextInt();

        tree = new TreeNode[vert];

        for(int i =0 ; i< edge ; i++)
        {

            tree[i] = new TreeNode();
        }


    }
*/
    static class Nodes{

    static int color[];
    static boolean visited[];
    static LinkedList<Integer> tree[];
}




   /* public ColorTree(int n)
    {
        for(int i =0; i< n;i++)
            Nodes.tree[i] = new LinkedList<>();
    }
*/

    public static void colortree(int q,int root) {
        Queue<Integer> bfs = new LinkedList<>();
        Set<Integer> colors = new LinkedHashSet<>();
        bfs.add(q);
        colors.add(Nodes.color[q-1]);  // addding the colour in Set (to avoid duplicates)

        Nodes.visited[q-1] = true;// removing the top element..
        Nodes.visited[root-1] = true ;
        while (!bfs.isEmpty()){

           int temp = bfs.poll();
            //System.out.println(" current node is = " + temp + "\n -----------------------------");
            /*for(int j =0 ; j < Nodes.tree[temp-1].size();j++)
            {

            }
*/


            Iterator<Integer> itr = Nodes.tree[temp-1].iterator();
            while(itr.hasNext()){
                Integer temp2 = itr.next();
                if(!Nodes.visited[temp2-1]){
                    //  System.out.print("next  node is --- " + );
                    colors.add(Nodes.color[temp2-1]);   // add the distinct color set
                    Nodes.visited[temp2-1] = true;        // making it visited so that (2,4) is not revisited as (4,2)
                    bfs.add(temp2);   // add to the queue next unvisited node
                }
            }
        }

        System.out.println(colors.size());
        colors.clear();  // clear the color
        bfs.clear();    // clear the queue
        Arrays.fill(Nodes.visited,false);


    }

    public static void main(String args[])   {

        int n,m,root,u,v;

        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        root = in.nextInt();
        Nodes.tree = new LinkedList[n];
        Nodes.visited = new boolean[n];
        Nodes.color = new int[n];

        for(int i =0; i< n;i++)
            Nodes.tree[i] = new LinkedList<>();    // initializing graph

        for(int i =0 ; i< n-1;i++)
        {
            u = in.nextInt();
            v = in.nextInt();

            if(v != root)
            Nodes.tree[u-1].add(v);

            Nodes.tree[v-1].add(u);    // This check is to ensure that root is not adjacent to anyone

        }

        for(int j =0;j<n ; j++)
            Nodes.color[j] = in.nextInt();

        for(int k =0 ; k< m ;k++)
            colortree(in.nextInt(),root);

    }
}
