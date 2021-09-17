package com.pepcoding.backtracking.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.BufferPoolMXBean;
import java.util.*;
import java.util.function.Function;

public class DistinctIslands {

    public static void main(String[] args) throws IOException {

        // read the input
       // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);
       int row = in.nextInt();
       int column = in.nextInt();


        int[][] islands = new int[row][column];
        boolean[][] visited  = new boolean[row][column];
        for(int i = 0 ; i < row; i++) {
            for (int j = 0; j < column; j++) {
                islands[i][j] = in.nextInt();
            }
        }

        HashMap<String,String> map = new HashMap<>();


        String islandsPath = "";

        for(int i = 0 ; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if(!visited[i][j])
                    islandsPath = doDistinctIslands(islands, visited, i, j, "",map);
                if(!map.containsKey(islandsPath))
                    map.put(islandsPath,islandsPath);
            }
        }

        System.out.println("no of distinct islands = " + map.size());
        System.out.println("Distinct islands  = " + map);
    }

    public static String doDistinctIslands(int[][] islands , boolean visited[][], int i, int j, String psf, Map<String,String> map){

        // check if the terminating condition is met
        // in this cases its the boundary
        if((i < 0 || i >= islands.length) || (j < 0 || j >= islands[0].length) || visited[i][j] || islands[i][j] == 0 ){
            return psf;
        }

        visited[i][j] = true;

        // do bfs/dfs by calling your neighbours
       psf = doDistinctIslands(islands,visited,i+1,j,psf+""+islands[i][j],map);
        psf = doDistinctIslands(islands,visited,i-1,j,psf+""+islands[i][j],map);
        psf = doDistinctIslands(islands,visited,i,j+1,psf+""+islands[i][j],map);
        psf = doDistinctIslands(islands,visited,i,j-1,psf+""+islands[i][j],map);
        return psf;
    }

}
