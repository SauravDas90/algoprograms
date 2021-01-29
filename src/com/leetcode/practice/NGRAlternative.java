package com.leetcode.practice;

import java.util.Arrays;
import java.util.Stack;
//  [3, 4, 3, 3, 3, 4, -1]
public class NGRAlternative {
    public static int[] getNGRAlternate(int[] arr){
        Stack<Integer> ngrstack = new Stack<>();
        int[] ngrArr = new int[arr.length];
        for(int i=0;i< arr.length; i++){
            // 3 conditions are basically imp to look
            // 1. if the stack is empty, just put element into it, to find the next bigger element
            // 2. if the stack is not empty
            //    a. until the stack top is < arr[i], it should be popped, as ngr has arrived,
            //    b. if the stack top is >= arr[i] , just push inside it,as we know
            if(!ngrstack.isEmpty()){
                while(!ngrstack.isEmpty() && arr[ngrstack.peek()]< arr[i]){
                    ngrArr[ngrstack.peek()] = arr[i];
                    ngrstack.pop();
                }
            }


            ngrstack.push(i);
        }
        // if the stack is left with elements it means, those elements didnt, have any NGR
        // The stack may or may not be empty
        // byDefault last element will always be -1
        while(!ngrstack.isEmpty()) {
            ngrArr[ngrstack.peek()] = -1;
            ngrstack.pop();
        }
        return ngrArr;
    }

    public static void main(String[] args) {
      //  int[] arr = {1,3,0,0,-1,3,4};
        int[] arr = {4,3,2,1};
        int[] ngrArr = getNGRAlternate(arr);
        System.out.println("ngrArr = " + Arrays.toString(ngrArr));
    }

}
