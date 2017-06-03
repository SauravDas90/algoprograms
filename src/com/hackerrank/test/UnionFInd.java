package com.hackerrank.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by saurav on 18/5/17.
 */

class Subset{
    int rank = 1;
    int parent;
    ArrayList<Integer> subset;

}
class Nodes2 implements Comparable<Nodes2>{

    int src;
    int dest;
    int cost;

    @Override
    public int compareTo(Nodes2 o) {
        return this.cost<o.cost? -1:1;   // easiest logic (ascending, smaller should return -1, else descending
    }
}
public class UnionFInd {

    static Subset[] subsets;
    static Nodes2[] nodes;
    static int n,m,src,dest,sum=0,parent[] ;
    public static void main(String args[]){


        Scanner in = new Scanner(System.in);
         n = in.nextInt();
        parent = new int[n];
         m = in.nextInt();
        nodes = new Nodes2[m];

        subsets = new Subset[m];


        for(int i =0 ; i<m ; i++){
            nodes[i] = new Nodes2();
            src =nodes[i].src = in.nextInt();
            dest = nodes[i].dest = in.nextInt();
            nodes[i].cost = in.nextInt();
           /* parent[dest-1] = nodes[i].dest;
            parent[src-1] = nodes[i].src;
*/
            subsets[i] = new Subset();
            subsets[i].parent = i+1;
            subsets[i].subset = new ArrayList<>();
            subsets[i].subset.add(i+1);

        }

        Arrays.sort(nodes);
        UnionFInd u = new UnionFInd();

        int dest,src,y,z;
        for(int i =0 ; i<m; i++){
            System.out.println("\t dest is = "+ nodes[i].dest + "\t\t src is = "+nodes[i].src);
            z = nodes[i].dest ;
            y = nodes[i].src ;
            dest= u.find(nodes[i].dest);
            src = u.find(nodes[i].src);

            System.out.println("\t find of dest is = "+ dest + "\t\t find of src is = "+ src);
            if(dest == 0 && src == 0) {
                u.union(nodes[i].src, nodes[i].dest, subsets);
                sum += nodes[i].cost;
            }
            else if(dest == src || subsets[z-1].subset.contains(y) || subsets[y-1].subset.contains(z))
                continue;
            else {
                u.union(nodes[i].src, nodes[i].dest, subsets);
                sum += nodes[i].cost;
            }

            System.out.println("\t cost is = "+ sum);
        }

       /* for(int i =0 ; i < n ; i++ )
            System.out.print(parent[i]);

        System.out.print("cost is = "+ sum);*/

    }

    public int find(int x){
        if(subsets[x-1].parent!= x ) {
            System.out.println("---find is " + subsets[x-1].parent );
            find(subsets[x - 1].parent);
        }
        return subsets[x-1].parent;

    }

    public void union(int x, int y,Subset subset[]){


        if(subset[x-1].rank > subset[y-1].rank) {
            subset[y - 1].parent = x;
            subset[x - 1].subset.add(y);
            subset[y-1] = subset [x-1];

        }
        else if(subset[x-1].rank < subset[y-1].rank) {
            subset[x - 1].parent = y;
            subset[y - 1].subset.add(x);
            subset[x-1] = subset [y-1];
        }
        else {
            subset[y-1].parent = x;
            subset[x-1].rank += 1;
            subset[x - 1].subset.add(y);
            subset[y-1] = subset [x-1];
        }
    }

}
