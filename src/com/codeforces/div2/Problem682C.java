package com.codeforces.div2;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by saurav on 1/6/17.
 */
public class Problem682C {

    static class Node {

        int node;
        int dist;
        int vertnumber;

        public Node(int node, int dist, int vertnumber) {
            this.node = node;
            this.dist = dist;
            this.vertnumber = vertnumber;
        }
    }

    static int a[],n;
    static ArrayList<Node>[] sadtree;
    static boolean visited[];

    public static void main(String args[])
    {


        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        a = new int[n];
        visited = new boolean[n];
        sadtree = new ArrayList[n];
        for(int i=0;i<n;i++){
            a[i] = in.nextInt();
            sadtree[i] = new ArrayList<>();
        }

        //sadtree[n-1] = new ArrayList<>();
/// since assignment is done till n-1

        for(int i=1;i<n;i++){
            int u = in.nextInt();
            int v = in.nextInt();
            Node n = new Node(u,v,a[u-1]);
            sadtree[i].add(n);
            n = new Node((i+1),v,a[i]);
            sadtree[u-1].add(n);
        }

        visited[0] = true;
      //  dfs((sadtree[0].get(0)).node-1,0,sadtree[0].get(0).dist);
        dfs(0,0,0);


        System.out.print(sadtree.length);


    }


    static int dfs(int previous,int current,int currdist){
        Iterator<Node> itr = sadtree[current].listIterator();
        int deg = sadtree[current].size(); // check if the node is a leaf node or not
        while(itr.hasNext()){
            Node n = itr.next();
            if(sadtree[n.node-1].size() == 1 && !visited[n.node-1]){
                visited[n.node-1] = true;
                if(n.vertnumber < n.dist+currdist);
                {
                    itr.remove();
                    deg--; // immediately return the call to previous node with value 1 indication node is removed
                }
            }
            else if(sadtree[n.node-1].size() >1 && !visited[n.node-1])
            {
                visited[n.node-1] = true;
                deg-=dfs(current,n.node-1,currdist+n.dist);
            }

        }
        if(deg == 1)
        {
            Iterator<Node> it = sadtree[previous].listIterator();
            while(it.hasNext())
            {
                Node n =it.next();
                if(n.node == current +1){
                    if(currdist > n.vertnumber) {
                        it.remove();
                        return 1;
                    }

                }

            }
        }
        return 0;
    }

}
