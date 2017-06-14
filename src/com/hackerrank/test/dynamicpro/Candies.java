package com.hackerrank.test.dynamicpro;

import java.util.Scanner;
//import java.util.stream.*;

/**
 * Created by saurav on 14/6/17.
 */
public class Candies {

    static int no,score[],candy[];
    public static void main(String args[]){
        int sum =0;
        Scanner in = new Scanner(System.in);
        no = in.nextInt();
        score = new int[no];
        candy = new int[no];
        for(int i =0; i<no ; i++)
            score[i] = in.nextInt();

        for(int i =0; i<no ; i++)
        leastCostCandy(candy,i);

        for (int i:candy
             ) {
           // System.out.print("\t" + i);
            sum += i;
        }

        //int sum = IntStream.of(a).sum(); use java 8
        System.out.println();

    }

    static void leastCostCandy(int []a,int index)
    {
        int temp;
        if(index == 0)
        {
            if(score[index] > score [index+1])
                candy[index] = candy[index+1]+1;
            else if(candy [index+1] > 1)
                candy[index] = candy[index+1]-1;
            else
                candy[index] = 1;
        }

        else if((index > 0 && index < no-1)){
            if(score[index] > score [index+1] && score[index] > score [index-1])
                candy[index] = Math.max(candy [index+1],candy [index-1]) + 1;
            else if(score[index] < score [index+1] && score[index] < score [index-1]){
                temp = Math.min(candy [index+1],candy [index-1]) - 1;
                candy[index] = (temp <= 1)? 1: temp;
            }
            else if(score[index] > score [index-1])
                candy[index] = candy[index-1]+1;
            else if(score[index] > score [index+1]){
                candy[index] = candy[index+1] +1;
                leastCostCandy(candy,index-1);
            }

        }

        else if(index == no-1)
        {
            if(score[index] > score [index-1])
                candy[index] = candy[index-1]+1;
            else if(candy [index-1] > 1)
                candy[index] = candy[index-1]-1;
            else
                candy[index] = 1;
        }

    }
}
