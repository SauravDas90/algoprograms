package com.leetcode.practice.binsearch;

public class FindFirstOccurence {
    public static void main(String[] args) {
        int arr[] = {1,2,3,3,3,3,6,7};
      int firstindex=   binSearch(arr,3);
        binSearch(arr,4);
       int lastindex= lasOccurBinSearch(arr,3);
        lasOccurBinSearch(arr,4);
        System.out.println("total count  = " + (lastindex-firstindex+1));
    }

    public static int binSearch(int arr[], int k){
        int start=0;
        int end = arr.length-1;
        int mid = -1;
        int firstOccurindex = -1;
        while(start <= end){
            mid = start+ (end-start)/2;

            if(k < arr[mid])
                end = mid-1;
            else if(k > arr[mid])
                start = mid+1;
            else{
                firstOccurindex = mid;
                end = mid-1; // look for in the left part, as the right part will be sorted
                                // This narrows down the search space , when you encounter the mid part
            }

        }

        System.out.println("firstOccurindex = " + (firstOccurindex+1)  );
        return firstOccurindex+1;
    }

    public static int lasOccurBinSearch(int arr[],int k){
        int start = 0;
        int end = arr.length-1;
        int mid = -1;
        int lastOccurIndex = -1;
        while(start <= end){
            mid = start + (end-start)/2;  // Never forget to put this inside loop
            if(k< arr[mid])
                end = mid-1;
            else if (k> arr[mid])
                start = mid+1;
            else{
                lastOccurIndex = mid;
                start = mid+1; // arbitrary choice to go right or left
            }

        }
        System.out.println("lastOccurIndex = " + (lastOccurIndex+1));
        return lastOccurIndex+1;
    }
}
