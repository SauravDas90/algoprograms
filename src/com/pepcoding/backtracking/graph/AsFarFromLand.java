package com.pepcoding.backtracking.graph;

import java.util.LinkedList;
import java.util.Queue;

class BFSNode{
    int x;
    int y;

    BFSNode(){
        x = -1;
        y = -1;
    }

    BFSNode(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class AsFarFromLand {


    public static void main(String[] args) {

        Queue<BFSNode> queue = new LinkedList<>();
        int grid[][] = {{1,0,1},{0,0,0},{0,0,0}};
        for (int i = 0; i < grid.length ; i++) {
            for (int j = 0; j < grid.length; j++) {
                if(grid[i][j] == 1){
                    // adding the points for multi source bfs
                    queue.offer(new BFSNode(i,j));
                }
            }
        }

        maxDistance(grid,queue);

    }

    public static int maxDistance(int grid[][],Queue<BFSNode> queue){
        int node[][] = {{0,1},{1,0},{0,-1},{-1,0}};
        int counter = 0 ;
        int dist [][] = new int[grid.length][grid.length];

        boolean visited[][] = new boolean[grid.length][grid.length];
        while(! queue.isEmpty()){

            counter++;
            int noOfElements = queue.size();

            for(int i  = 0 ;i < noOfElements ; i++){
                BFSNode currNode =  queue.poll();
                // marking the current node as true;
                visited[currNode.x][currNode.y] = true;

                for(int[] elem:node){
                    // check
                    int updatedX= elem[0]+ currNode.x;
                    int updatedY = elem[1]+ currNode.y;
                    if(updatedX<0 || updatedX >= grid.length || updatedY<0 || updatedY >= grid.length || visited[updatedX][updatedY] || grid[updatedX][updatedY] == 1  ){
                        continue;
                    }
                    // else

                    visited[updatedX][updatedY] = true;
                    queue.offer(new BFSNode(updatedX,updatedX));
                    dist[updatedX][updatedY] =(int) Math.abs(currNode.x-updatedX)+ Math.abs(currNode.y-updatedY);
                }

            }


        }

        int maxDistance = Integer.MIN_VALUE;
        for (int i = 0; i < grid.length ; i++) {
            for (int j = 0; j < grid.length; j++) {
                if(dist[i][j] > 0){
                    maxDistance = Math.max(maxDistance, dist[i][j]);
                }
            }
        }// max value

        return maxDistance;
    }
}
