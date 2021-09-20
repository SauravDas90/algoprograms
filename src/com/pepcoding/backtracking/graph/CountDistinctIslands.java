package com.pepcoding.backtracking.graph;

import java.util.Scanner;

public class CountDistinctIslands {

    // need to find a solution to print the String without making it static
    private static String psf ="";
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();

        int[][] region = new int[row][col];
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row ; i++) {
            for (int j = 0; j < col; j++) {
                region[i][j] = in.nextInt();
            }
        }


        for (int i = 0; i < row ; i++) {
            for (int j = 0; j < col; j++) {
                if(region[i][j] == 1 && !visited[i][j]){
                    doCountDistinctIslands(region, i,j, visited,"x");
                    System.out.println("psf = " + psf.toString());
                    psf="";
                }

            }

        }


    }

    public static void doCountDistinctIslands(int[][] region, int row, int col, boolean visited[][],String current){
    // check to see if things work
        if((row < 0 || row >= region.length) || (col < 0 || col >= region[0].length) || visited[row][col] || region[row][col] == 0)
        {
            return;
        }
        // add the string only when the it has been verified that its visited, adding before it is not correct
        psf+=current;
        visited[row][col] = true;
            doCountDistinctIslands(region, row, col+1, visited,"r");
            doCountDistinctIslands(region, row, col-1, visited,"l");
            doCountDistinctIslands(region, row +1, col, visited, "u");
            doCountDistinctIslands(region, row-1, col, visited, "d");

        // after the elements have been visited and all the subsequent dfs , done append Z
        psf+="z";

    }
}
