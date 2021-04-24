package com.leetcode.practice.binsearch;

public class KRotatedSortedArray {

    public static void main(String[] args) {
        int arr1[] = {1,2,3,4,5,6,7};
        int arr[] = {3,4,5,6,7,1,2};
        int arr2[] = {5,6,7,1,2,3,4};

        int krotaed = findRotation(arr);
        System.out.println("krotaed = " + (krotaed));
        int krotaed1 = findRotation(arr2);
        System.out.println("krotaed = " + (krotaed1));
    }
    public static int findRotation(int arr[]){
        int pivot = 0; // assuming the array starts at 0;
        int start = 0;
        int len = arr.length;
        int end = len-1;
        int mid = -1,prev = -1;
        // if the array is sorted , with no rotation
        while(start <= end) {
            if (arr[start] <= arr[end]) {
                return start; // no rotation
            }
            mid = start + (end-start)/2;
            prev = (mid + len -1) % len;

            if(arr[prev] > arr[mid]){
                pivot = mid;
                return pivot;
            }
            else if(arr[start] <= arr[mid])
                start = mid+1;
            else if(arr[mid] <= arr[end])
                end = mid-1;
        }




        return pivot;
    }
}
