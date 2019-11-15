package com.hackerrank.test;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


public class MinimumSwap {

    // Complete the minimumSwaps function below.
   /* static int minimumSwaps(int[] arr) {
        int temp = 0, swaps = 0 ,min = arr[0] , min_pos =0;
        for (int k = 1 ; k < arr.length ; k++){
            if(arr[0] > arr[k])
            {
                min_pos = k;
                min = arr[k];
            }
        }

        if(min_pos != 0){
            temp =arr[min_pos];
            arr[min_pos] = arr[0];
            arr[0] = temp;
            ++swaps;
        }

        System.out.println("  array value is ");

        for (int i = 0; i < arr.length;) {
            if (arr[i] != min + 1){
            System.out.println("Swapping --"+arr[arr[i] - 1] +"  AND -- "+arr[i]);

                temp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = temp;
                ++swaps;
            } else
                ++i;
         System.out.println("value at position -- "+ i +" is set to -- "+ arr[i]);
        }
        return swaps;

    }
*/

    static void minimumBribes(int[] q) {
/*
        int min_count =0;

        for(int j=0;j< q.length;j++){

            int diff = (j+1) - q[j];

            if((diff * -1 ) > 2){
                System.out.println("Too chaotic");
                return;
            }
            if(diff > 0){
                System.out.println("diff is "+diff+" ---q[j] value is "+ q[j]);
                for(int k=j-1; k>=0;k--){

                    if(q[j] < q[k]) {
                        ++min_count;
                        System.out.println("value of q[k]"+q[k] +" k is-- "+ k);
                    }
                   */
/* else
                        break;*//*

                }
            }

        }
        System.out.println(min_count+1);
*/

        int ans = 0;
        for (int i = q.length - 1; i >= 0; i--) {
            if (q[i] - (i + 1) > 2) {
                System.out.println("Too chaotic");
                return;
            }
            int j = Math.max(0, q[i] - 2);
            System.out.println("value of j is "+ j);

            for (; j < i; j++) {
                if (q[j] > q[i])
                    ans++;
            }
            System.out.println("answer value for i --- "+i+" -- is "+ ans);

        }
        System.out.println(ans);


    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)  {
       // BufferedWriter bufferedWriter = new BufferedWriter(new InputStreamReader(System.in));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

       // int res = minimumSwaps(arr);
        minimumBribes(arr);

        /*bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();*/

       // System.out.println("minimu no of swap for an input is "+ res);


        scanner.close();
    }
}
