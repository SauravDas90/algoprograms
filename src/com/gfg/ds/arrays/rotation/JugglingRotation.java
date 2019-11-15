package com.gfg.ds.arrays.rotation;

public class JugglingRotation {

    public static void main(String args[]){

        int arr[] = {1,2,3,4,5,6,7,8,9};
        int len = arr.length;
        int rot = 3; // left rotate by 3 steps

        for(int i = 0 ; i<rot; i++){
            int x = i;
            int temp = arr[x%len];
            while((x+rot)% len != i){
                arr[x%len] = arr[(x+rot)%len];
                x+=rot;
            }
            arr[x%len] = temp;

            for(int j = 0; j<len ; j++){
                System.out.print(arr[j]+"\t");
            }
            System.out.println();

        }
    }
}
