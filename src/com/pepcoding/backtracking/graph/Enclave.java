package com.pepcoding.backtracking.graph;

import com.leetcode.practice.LinkedList;

import java.util.Scanner;

public class Enclave {

    private static int count;
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();

        int region[][]= new int[row][col];
        boolean visited [][] = new boolean[row][col];

        for(int i =0 ; i< row; i++){
            for(int j = 0 ; j< col ; j++){
                region[i][j] = in.nextInt();
            }
        }

        // move from left to right in both top and bottom row
        int top = 0, bottom = region.length-1;
        for (int i = 0; i < col; i++) {
            // in other ones checking if its visisted is important, not in this case
            if(region[top][i] == 1 ) {

                doEnclave(region, visited, top, i);

            }
            if(region[bottom][i] == 1 ) {

                doEnclave(region, visited, bottom, i);
            }
        }

        // move from top to bottom
        for (int j = 1; j < row-1; j++) {
            if(region[top][j] == 1 ) {

                doEnclave(region, visited, j, top);

            }
            if(region[bottom][j] == 1 ) {

                doEnclave(region, visited, j, bottom);
            }
        }

        System.out.println("count = " + count);

    }

    public static void doEnclave(int region[][], boolean visited[][],int row, int col){
        if((row <0 || row >= region.length)|| (col < 0 || col >= region[0].length) || visited[row][col] || region[row][col] == 0){
           return;
        }

        // the dfs may get into a loop, even though the elements in the outer row & column need not visited
        visited[row][col] = true;
        if((row >0 && row < region.length-1) && (col > 0 && col < region[0].length-1) ){
            count++;
        }


        doEnclave(region, visited, row, col+1);
        doEnclave(region, visited, row, col-1);
        doEnclave(region, visited, row+1, col);
        doEnclave(region, visited, row-1, col);


    }
}
