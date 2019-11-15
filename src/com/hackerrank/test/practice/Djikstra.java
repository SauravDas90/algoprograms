package com.hackerrank.test.practice;


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.regex.*;
public class Djikstra {

   private static int no_of_vertices =0;
    // Complete the shortestReach function below.
    private static int[] shortestReach(int n, int[][] edges, int s) {
        int dist[] = new int[n];
        boolean visited[] = new boolean[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        Arrays.fill(visited,false);
        dist[s-1] = 0; //starting node initialised to zero
        int nextNode =0, currnode = s-1;
        for(int j =0 ;j<n;j++){
            visited[currnode] = true;
            int minDist = Integer.MAX_VALUE;
            for(int k =0 ;k<no_of_vertices;k++){


                if( edges[k][0] == (currnode+1) ){//&& !visited[(edges[k][1])-1]){
                    if(dist[(edges[k][1])-1]> (edges[k][2]+dist[currnode])){
                        dist[(edges[k][1])-1] =edges[k][2]+dist [currnode];
                    }
                    if(  dist[(edges[k][1])-1] < minDist ){
                        nextNode = (edges[k][1])-1;
                        minDist = dist[(edges[k][1])-1];
                    }
                }
            }

            currnode = nextNode;
        }

        return dist;


    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);
            no_of_vertices =m;
            int[][] edges = new int[m][3];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 3; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                   edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = shortestReach(n, edges, s);

            for (int i = 0; i < result.length; i++) {
              //  bufferedWriter.write(String.valueOf(result[i]));
                System.out.print(result[i] +" ");

            }

           // bufferedWriter.newLine();
        }

       // bufferedWriter.close();

        scanner.close();
    }

}
