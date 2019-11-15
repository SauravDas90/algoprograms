package com.gfg.ds.arrays.rotation;

import java.util.*;

public class RotationCountSorted {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for(int k=0;k<t;k++){
            int n = in.nextInt();
            int arr[]= new int[n];
            for(int r = 0; r<n;r++){
                 arr[r] = in.nextInt();

            }

            System.out.println(binarySearch(arr,0,arr.length-1));

        }
       /* int arr[] = {5,6,7,8,9,1,2,3,4};
        int len = arr.length;
        System.out.println(binarySearch(arr,0,len-1));*/

    }

    public static int binarySearch(int arr[],int left,int right){

        if(left == right)
            return 0;
        int len = arr.length;
        int pivot = ((left+right)/2)%len;
       // int leftResult,rightResult;
/*
        if(arr[pivot%len] <= arr[(pivot+1)%len])
            binarySearch(arr,pivot,right);
        else
            return pivot%len;

        if(pivot ==0) {
            if(arr[pivot] <= arr[pivot+1])
                 return 0;
            else
                return 1;
        }

        if(arr[(pivot-1)%len]<= arr[pivot%len])
            binarySearch(arr,left,pivot);
        else
            return (pivot-1)%len;

        return 0;*/

        if((arr[(pivot-1)%len] <= arr[pivot%len]) && (arr[pivot%len] <= arr[(pivot+1)%len])){
            if((left%len)<=(pivot-1)%len)
              return  binarySearch(arr,left,pivot);
            if((pivot+1)%len<=(right%len))
            binarySearch(arr,pivot+1,right);
        }
        else if(arr[(pivot-1)%len]>arr[pivot%len])
            return pivot;
        else if(arr[pivot%len]>arr[(pivot+1)%len])
            return pivot+1;
        return 0;

    }
}
