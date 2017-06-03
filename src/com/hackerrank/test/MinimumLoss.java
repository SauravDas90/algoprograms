package com.hackerrank.test;

import java.util.Scanner;

/**
 * Created by saurav on 8/5/17.
 */
public class MinimumLoss {

    public static void main(String args[]) {

        int n,p[];

        Scanner in = new Scanner(System.in);
        n=in.nextInt();
        p = new int[n];

        for(int i=0;i<p.length;i++){
            p[i] = in.nextInt();
        }

        for(int j=2;j<(p.length);j++){
            int k=j-1,temp = p[j];
            while(k>=0 && temp<p[k]){
                p[k+1] = p[k];
                k--;
            }

            p[k+1] = temp;
        }

        int min = p[1] - p[0];
        for(int i=1;i<p.length-1;i++){
            if(p[i+1]-(p[i+1]-p[i])< min)
                min = p[i+1]-p[i];
        }

        System.out.println(min);
        in.close();

    }
}