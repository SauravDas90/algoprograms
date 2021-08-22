package com.leetcode.practice;

public class TrappingRainWater {

    public static void main(String args[]){

    int[] waterlevel = {4,2,0,3,2,5};
     //   int[] waterlevel = {0,1,0,2,1,0,1,3,2,1,2,1};
        getTrappedWater(waterlevel);
    }


    public static void getTrappedWater(int[] waterlevel){

        int maxLevelNow = -1;
        int[] globalMaxLeft, globalMaxRight;
        globalMaxLeft = new int[waterlevel.length];
        globalMaxRight = new int[waterlevel.length];

        maxLevelNow = globalMaxLeft[0] = waterlevel[0];


        for(int i = 1 ; i < waterlevel.length; i++)
        {
           /* if(waterlevel[i-1] > maxLevelNow ){
                globalMaxLeft[i] = waterlevel[i-1];
                maxLevelNow = waterlevel[i-1];
            }
            else
                globalMaxLeft[i] = maxLevelNow ;*/
           globalMaxLeft[i] = Math.max(globalMaxLeft[i-1],waterlevel[i]);

        }

        maxLevelNow =  globalMaxRight[waterlevel.length-1] = waterlevel[waterlevel.length-1];

        for(int i = waterlevel.length-2 ; i >= 0; i--)
        {

 /*           if(waterlevel[i+1] > maxLevelNow ){
                globalMaxRight[i] = waterlevel[i+1];
                maxLevelNow = waterlevel[i+1];
            }
            else
                globalMaxRight[i] = maxLevelNow ;*/
           globalMaxRight[i] = Math.max(globalMaxRight[i+1],waterlevel[i]);

        }
        int totalTrappedWater = 0;
        for(int i = 0 ; i < waterlevel.length; i++)
        {
            totalTrappedWater += Math.min(globalMaxRight[i],globalMaxLeft[i]) - waterlevel[i];
            // Made mistake of Using Math.max instead of Math.min (be very careful in using it
        }

        System.out.println("Rain Trapping water "+ totalTrappedWater);

    }


}
