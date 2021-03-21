package com.pepcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class KPartitionProblem {

    public static void main(String[] args) {
        int k = 3; // enter the no of partitions;
        int[] arr  = {2,3,4,5};

        List<List<Integer>> kpartitions = new ArrayList<>(k);
        for(int i = 0 ; i < k ; i++)
            kpartitions.add(new ArrayList<>());
        createKPartitions(arr,kpartitions,0,0);
    }

    public static void createKPartitions(int[] arr,List<List<Integer>> createkpartitions, int noUsedSoFar, int arrayUsed  ){
        if(noUsedSoFar >= arr.length){
            // print the arraylist;
            if(arrayUsed >= createkpartitions.size()){
                for(List<Integer> nos:createkpartitions)
                    System.out.print( nos.toString() + " ");
            }
            System.out.println();

            return;
        }
       for(int i = 0; i < createkpartitions.size(); i++){
           if(0 == createkpartitions.get(i).size()){
               createkpartitions.get(i).add(arr[noUsedSoFar]);
               createKPartitions(arr,createkpartitions,noUsedSoFar+1,arrayUsed);
               //backtrack
               createkpartitions.get(i).remove(createkpartitions.get(i).size()-1);
               break; // use break after backtracking
           }
           else{
               createkpartitions.get(i).add(arr[noUsedSoFar]);
               createKPartitions(arr,createkpartitions,noUsedSoFar+1,arrayUsed+1);
               //backtrack
               createkpartitions.get(i).remove(createkpartitions.get(i).size()-1);
           }
       }
    }
}
