package com.leetcode.dp.knapsack.unbounded;

public class CoinChange2 {

    public static void main(String[] args) {
        int coins[] = {1,2,5};
        change(5,coins);
    }

    public static int change(int amount, int[] coins) {

        int noOfWays[][] = new int[coins.length+1][amount+1];

        for(int j=0;j<=amount;j++)
            noOfWays[0][j] = 0;

        for(int i=0;i<=coins.length;i++)
            noOfWays[i][0] = 1;


        for(int i =1;i<=coins.length;i++){
            for(int j =1;j<=amount;j++){

                if(j < coins[i-1])
                    // coin not chosen
                    noOfWays[i][j] = noOfWays[i-1][j];
                else
                    noOfWays[i][j] =  noOfWays[i-1][j]+ noOfWays[i][j-coins[i-1]];
                //coin not chosen+ coin chosen (notice the [i][j-coins[i-1]])
                //System.out.print(" "+noOfWays[i][j] );
            }
            //System.out.println();
        }

        return noOfWays[coins.length][amount] ;

    }
}
