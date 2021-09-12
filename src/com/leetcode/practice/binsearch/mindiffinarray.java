package com.leetcode.practice.binsearch;

public class mindiffinarray {

    public static void main(String args[]){
       // int arr[] = IntStream.of(1,11).toArray();
        int arr[]= {1,2,3,4,5,6,7,8,9,10};
        int k = 10;
      //  int k = 0;
       // int k =5;
        System.out.println("differnce element " + findMinDiffelem(arr,k));
    }

    public static int findMinDiffelem(int arr[], int k){
        int start = 0;
        int end = arr.length -1;
        int mid = -1;

        while(start <= end){
             mid = start +(end-start)/2;

             if(arr[mid] == k){
                 return 0; // no difference to be found
             }
             else if(arr[mid] >k ){
                 end = mid-1;
             }
             else
                 start = mid+1;
        }

        if(start >= arr.length)
            return (int) Math.abs(k-arr[end]);
        if(end < 0)
            return (int) Math.abs(k-arr[start]);
        return (int) Math.abs(k-arr[end]) < Math.abs(k-arr[start]) ? Math.abs(k-arr[end]) : Math.abs(k-arr[start]);

    }

}

// 1. ber careful about the code , write this way  k > arr[mid], start = mid+1
//                                                  k < arr[mid] end = mid-1
// 2. always check for overflow and underflow when using mid-1 in or outside the loop
