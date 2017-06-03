package com.codeforces.div2;

import java.util.Scanner;

/**
 * Created by saurav on 28/5/17.
 */
public class Potion {



    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        int s = in.nextInt();
        int[] firstspeed = new int[m+1];
        int[] firstcost = new int[m+1];
        firstspeed[0]=x;
        firstcost[0]=0;
        for(int i = 1; i < firstspeed.length; i++){
            firstspeed[i]=in.nextInt();
        }
        for(int i = 1; i < firstspeed.length; i++){
            firstcost[i]=in.nextInt();
        }
        int[] boost = new int[k+1];
        int[] boostcost = new int[k+1];
        for(int i = 1; i < boost.length; i++){
            boost[i]=in.nextInt();
        }
        for(int i = 1; i < boost.length; i++){
            boostcost[i]=in.nextInt();
        }
        long time = Long.MAX_VALUE;
        for(int i = 0; i < firstspeed.length; i++){
            int canspend =s-firstcost[i];
            if(canspend<0)continue;
            //System.out.println(canspend);
            int idx = binsearch(boostcost, canspend);
            long curtime = (Math.max(0,n-boost[idx]*1l))*firstspeed[i];
            time = Math.min(curtime, time);
        }
        System.out.println(time);
    }

    public static int binsearch(int[] arr, int val){
        int lo = 0;
        int hi = arr.length-1;
        while(lo<hi){
            int mid = (hi+lo+1)/2;
            if(arr[mid]>val){
                hi=mid-1;
            }else{
                lo=mid;
            }
        }
        return lo;
    }

}
