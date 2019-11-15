package com.gfg.ds.arrays.rotation;

import java.util.Arrays;

public class ArrayRotation {

    public static void main(String args[]){
        int arr[] = {1,2,3,4,5};
        int n = arr.length;

        for(int r =0 ;r<3;r++) {
            int temp = arr[n-1];
            for (int i = (n - 1) - 1; i >= 0; i--) {
                arr[(i + 1) % n] = arr[i];
            }
            arr[0] = temp;

            for(int i:arr)
                System.out.print(i+"\t");
            System.out.println();


        }

        for(int r =0 ;r<3;r++){
            int temp = arr[0];
            for (int i = 1; i < n; i++) {
                arr[(i - 1) % n] = arr[i];
            }
            arr[n-1] = temp;

            for(int i:arr)
                System.out.print(i+"\t");
            System.out.println();
        }


    }
}
