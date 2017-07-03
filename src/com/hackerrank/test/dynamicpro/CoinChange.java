package com.hackerrank.test.dynamicpro;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by saurav on 16/6/17.
 */
public class CoinChange {

    static long[] noways;
    static long[] c;

    public static void main(String args[]){

        int n,m;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        c = new long[m];
        noways = new long[n+1];
        for(int c_i=0; c_i < m; c_i++){
            c[c_i] = in.nextLong();
        }
        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'
        noways[0] = 0L;  // Initialize for 0 coins
        getWays(n, c);
System.out.print(Arrays.toString(noways));   // this shortcut is used to print the entire array

    }


    static long getWays(int n , long a[]){

            if(n == 1 || n==0)
               return noways[n] = n;

            else if(noways[n-1] != 0L)
               return noways[n-1];
           /* if(n>1)
            {
             for(int t = 1; t<= n; t++)   {
               //  if(n-t > 1)
               //  noways[n-t] += getWays(n-t,noways)+getWays(t,noways);
               //  if(noways[t-1] == 0)
               //      noways[t-1] = getWays(t-1,noways);

                 noways[n] += getWays(n-t,noways)+noways[t];

               //  if(t >=2)
                 //    noways[n-t] += getWays(t,noways);
             }

            }*/

        for(int j = 2;j<n;j++)
        {
            for(long k:c)
            {
             if(k <=j) {

                 noways[j] += getWays(j-(int)k,noways)+noways[(int)k];

             }

            }
        }


        return noways[n];
    }
}
