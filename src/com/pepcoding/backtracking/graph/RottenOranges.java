package com.pepcoding.backtracking.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair{

    int x,y;
    Pair(){

    }

    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class RottenOranges {

    private static int fresh = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int arr[][] = new int[row][col];
        Queue<Pair> levelNodes = new LinkedList<>();

        for(int i = 0 ; i< row ; i++){
            for(int j =0 ; j< col ; j++){
                int elem = in.nextInt();
                if(elem == 2)
                    levelNodes.offer(new Pair(i,j));
                else if(elem == 1)
                    fresh++;
                arr[i][j] = elem;
            }
        }
        System.out.println(" initial fresh = " + fresh);
        if(fresh == 0)
            System.out.println("All oranges are rotten");
        else
            checkRottenOrange(arr,levelNodes);

    }

    public static void checkRottenOrange(int arr[][], Queue<Pair> levelNodes){

        int count = -1 , time = 3;
        while(! levelNodes.isEmpty()){

            /*count++;
            if(count>time)
                return;*/

            int noOfElements = levelNodes.size();

            // inner loop to find next elements
            for(int i = 0; i < noOfElements; i++){
                Pair currNode = levelNodes.poll();

                // call the 4 neighbours

                checkAndApplyNode(arr,currNode.x+1, currNode.y,levelNodes);
                checkAndApplyNode(arr,currNode.x-1, currNode.y,levelNodes);
                checkAndApplyNode(arr,currNode.x, currNode.y-1,levelNodes);
                checkAndApplyNode(arr,currNode.x, currNode.y+1,levelNodes);
            }
        }

        if(fresh > 0)
            System.out.println("there are still fresh oranges = " + fresh);
        else
            System.out.println("All the oranges are rotten now = ");

    }

    public static void checkAndApplyNode(int arr[][], int x, int y,Queue<Pair> levelNodes){
        // not only check for empty oranges but also for the Original rotten ones, as those are not
        // being modified at the same level
         if((x <0 || x >= arr.length) ||(y <0 || y >= arr[0].length) || arr[x][y] == 0 || arr[x][y] == 2){
             return;
         }

         // add to nodes for next level
        levelNodes.offer(new Pair(x,y));
         // reduce the fresh oranges
         fresh--;
         // mark it as visited
         arr[x][y] = 0;

    }

}
