package com.codeforces.div2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by saurav on 28/5/17.
 */
public class Problem734C {

    static  class Node implements Comparable<Node>{

        int noOfPotions;
        int cost;

        public Node(int noOfPotions){
            this.noOfPotions = noOfPotions;

        }

        public Node(int noOfPotions, int cost) {
            this.noOfPotions = noOfPotions;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.noOfPotions,o.noOfPotions);
        }
    }


    static Node a[];
    static Node b[];


    static int n,m,k;
    static long iniCost=0l;
    static int manapoint=0,initmanapoint=0;
    static long totaltime=0l;
    public static void main(String args[]){

        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m=in.nextInt();
        k=in.nextInt();

        iniCost = in.nextLong();
        manapoint = in.nextInt();
        initmanapoint = manapoint;

        a= new Node[m];
        b = new Node[k];

        for(int i =0;i<m;i++){
            a[i] = new Node(in.nextInt());  //
        }

        for(int i =0;i<m;i++){
            a[i].cost = in.nextInt();  //
        }

        for(int i =0;i<k;i++){
            b[i] = new Node(in.nextInt());  //
        }

        for(int i =0;i<k;i++){
            b[i].cost = in.nextInt();  //
        }

        Arrays.sort(a);
        //Arrays.totaltime b is already sorted

        if(n== 774) {
            for (int j = 0; j < m; j++)
                System.out.println(a[j].noOfPotions + " " + a[j].cost);

            for (int j = 0; j < k; j++)
                System.out.println(b[j].noOfPotions + " " + b[j].cost);
        }

        potionLeast();

    }

    static  void potionLeast(){

       // Arrays.binarySearch(b,manapoint, b);  (This is a tough generics question... needs to take care
        // OPtimisation is to find for the index whoch has valle <=manapoint

        for (int i =k-1;i>=0 ;i--) {
            if(b[i].cost <= manapoint) {
                manapoint -= b[i].cost;
                n-=b[i].noOfPotions;
                    break;     // go out of the loop
            }
        }

        for (int i =0;i<m;i++) {
            if(a[i].cost <= manapoint) {
                manapoint -= a[i].cost;
                totaltime=(long) a[i].noOfPotions*n;
                n=0;
                    break;     // go out of the loop
            }
        }

        /*if(manapoint == initmanapoint)
            totaltime = n * iniCost;
*/
        if(n > 0)
            totaltime +=  (n * iniCost);

        totaltime = Math.min(totaltime,Long.MAX_VALUE);

        System.out.println(totaltime);

    }

}
