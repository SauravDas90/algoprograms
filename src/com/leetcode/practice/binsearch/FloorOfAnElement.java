package com.leetcode.practice.binsearch;

public class FloorOfAnElement {
    public static void main(String[] args) {
        int arr[] = {1,2,4,5,6,8,9};
        findFloorElement(arr, (int) Math.floor(4.2));
        findFloorElement(arr, (int) Math.floor(10));
    }

    public static void findFloorElement(int arr[],int k){
        int start = 0;
        int end  = arr.length -1;
        int floor = -1;
        int mid;
        while(start <= end){
             mid = start + ( end- start)/2;
            if(k == arr[mid]){
                floor = mid;
                break;
            }
            else if(k < arr[mid]){
                end = mid-1;
            }
            else{
                floor = arr[mid];
                start = mid+1;
            }


        }
       // System.out.println("floor found at index,outside loop= " + (floor+1));
        System.out.println(" the floor element found here " + floor);
    }
}
// The answer will not give you a match always, it can provide the closest value that is available
//  . there are 2 conditions on having a floor
// 1. the floor may exist in the array
// 2. the floor may not exits in the array, in tha case, just return the closest element you can find in the array