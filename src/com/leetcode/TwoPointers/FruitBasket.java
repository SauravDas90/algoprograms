package com.leetcode.TwoPointers;

import java.util.Arrays;

public class FruitBasket {

    public static void main(String[] args) {
        //int arr[] = {3,3,3,1,2};
        int arr[] = {3,3,3,1,2,1,1,2,3,3,4,3,3,3,3,3,3,5,5,7,3,3,4};
        totalFruit(arr);
    }

    public static int totalFruit(int[] tree) {

        int len = tree.length;

        if(len<=1)
            return len;

        int[] lastIndx = new int[3];  // one more space for the additional value
        int[] elem = new int[3];

        Arrays.fill(lastIndx,-1);
        Arrays.fill(elem,-1);

        lastIndx[0] = 0; // first elemen into array
        elem[0] = tree[0];

        elem[1] = tree[1] == tree[0]? -1:tree[1];
        lastIndx[1] = elem[1] == -1? -1:1;
        lastIndx[0] = lastIndx[1] == 1?lastIndx[0] : lastIndx[0]+1;

        int winSize = 2;
        int maxWindow = 0;

        int loopCounter = 2; // startig of the loop  //lastIndx[0] < len-1 ||

        while(loopCounter<len ){

            if(tree[loopCounter] == elem[0]){
                lastIndx[0] = loopCounter;
                winSize++;
            }

            else if(tree[loopCounter] == elem[1]){
                lastIndx[1] = loopCounter;
                winSize++;
            }

            else if(lastIndx[1] == -1 && elem[1] == -1){
                lastIndx[1] = loopCounter;
                elem[1] = tree[loopCounter];
                winSize++;
            }
            else
            {
                if(lastIndx[0] < lastIndx[1])
                {
                    lastIndx[0] = lastIndx[1];
                    elem[0] = elem[1];

                }


                lastIndx[1] = loopCounter;
                elem[1] = tree[loopCounter];

                maxWindow = Math.max(maxWindow,winSize);
                winSize =  lastIndx[1] -  lastIndx[0];

            }

            loopCounter++;


        }

        return maxWindow;

    }
}
