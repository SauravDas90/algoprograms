package com.gfg.ds.arrays.rotation;

public class ReversalAlgorithm {

    public static void main(String args[]){

        int arr[] = {1,2,3,4,5,6,7,8,9};
        int len = arr.length;
        int rot = 3; // left rotate by 3 steps
        reverse(arr,rot+1,len-1);
       reverse(arr,0,rot);

        reverse(arr,0,len-1);


        for(int j:arr)
            System.out.print(j+"\t");
        System.out.println();


    }

    public static void reverse(int arr[],int first,int last){
        int no_of_times = (int) Math.floor((last-first-1)/2);

        for(int i =0;i<=no_of_times;i++){
            int temp = arr[last-i];
            arr[last-i] = arr[first+i];
            arr[first+i] = temp;
        }

    }
}
