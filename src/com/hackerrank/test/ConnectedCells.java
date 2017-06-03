package com.hackerrank.test;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by saurav on 12/5/17.
 */
public class ConnectedCells {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        // Queue<Integer> nextCell = new LinkedList<>();
        Map<Integer,Integer> nextCell = new LinkedHashMap<>();
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
                if(grid[grid_i][grid_j] == 1)
                    nextCell.put(grid_i,grid_j);
            }
        }

        int val =0;
        for(int nextKey:nextCell.keySet())
        {
                val = nextCell.get(nextKey);

        }


    }
}
