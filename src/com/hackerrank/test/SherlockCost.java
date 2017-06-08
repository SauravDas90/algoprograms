package com.hackerrank.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by saurav on 7/6/17.
 */
public class SherlockCost {
/*

    static int n,t,b[],finalarr[],cost[],temp1 [],temp2[];

    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        cost = new int[n];

        for(int i =0 ; i< n ; i++)
        {
            t = in.nextInt();
            b = new int [t];

            finalarr =new int [t];
            for(int j =0; j< t;j++)
                b[j]= in.nextInt();

        }

    }

    public static void cost(){
        int diff1,diff2,sum;
        sum = diff1 = diff2 =0 ;
        for(int i = 2 ; i<t-2;i++) {


            if (b[i] < b[i - 1])
                diff1 = Math.abs(1 - b[i - 1]);
            else
                diff2 = Math.abs(1 - b[i]);

            sum += Math.max(diff1, diff2) + Math.max(Math.abs(1-b[i+1]),Math.abs(b[i]));
        }
    }

    public static void calculate(int index, int value){
        temp1 = new int[index];
        temp2 = new int[index];
        int sum1=0,sum2 =0;

        temp1 = temp2 = Arrays.copyOf(b,index);
        for(int i = 2 ; i< index ; i+=2) {
            if(i=2)
                sum1 = Math.abs(b[0]-1);
            sum1 += (2 * Math.abs(b[i]));
        }
        for(int i = 1 ; i< index ; i+=2)
          sum2 += (2 * Math.abs(b[i]));
            
    }
*/



    static int t,n,b[],costsum =0 ;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();


        for (int i = 0; i < n; i++) {
            t = in.nextInt();
            b = new int [t];

            for (int j = 0; j < t; j++) {
                b[j] = in.nextInt();
            }
            cost(t-1);
            // call a method
            System.out.println(costsum);
        }

        in.close();
    }


    public static int cost(int i)
    {
        int lowcost =0, highcost =0;
       /* if(i == 1) {
           System.out.println( "----"+Math.max(Math.abs(b[1] - 1), Math.abs(b[0] - 1)));
            return Math.max(Math.abs(b[1] - 1), Math.abs(b[0] - 1));
        }
        else {
            k = Math.max(Math.abs(b[i] - 1), Math.abs(b[i - 1] - 1));
            System.out.println(k + " index is " + i);
            costsum = k + cost(i - 1);
            System.out.println(costsum + " index is " + i);
            return costsum;
        }*/

        int lo,hi;
        if(i == 1) {
            System.out.println( "----"+Math.max(Math.abs(b[1] - 1), Math.abs(b[0] - 1)));
            highcost  = Math.max(Math.abs(b[i]-1),Math.abs(b[i-1]-b[i]));
            lowcost = Math.max(Math.abs(b[i-1]-1),Math.abs(b[i-1]-b[i]));
            System.out.println(costsum + " index is 1 \t" + i);
            System.out.println(lowcost + " ----- " + highcost);
            return (costsum +=Math.max(lowcost, highcost));
        }

        else {

            hi = lowcost + Math.max(Math.abs(b[i]-1),Math.abs(b[i-1]-b[i]));
            lo = highcost + Math.max(Math.abs(b[i-1]-1),Math.abs(b[i-1]-b[i]));
            System.out.println(costsum + " index is not 1\t" + i);
            System.out.println(lo + " ----- " + hi);
            lowcost = lo;
            highcost = hi;
            return (costsum =Math.max(lowcost, highcost) + cost(i-1));
        }
    }

}
