package com.pepcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class EqualSumPartition {
    public static void main(String[] args) {
        //int[] numbers = {4,3,2,3,5,2,1};
        int[] numbers = {4,4,4,4,4};
        int sum = 0;
        int numberOfParts = 5;
        for(int i: numbers)
            sum+=i;

        if(sum < 0 || (numberOfParts < 0 || numberOfParts > numbers.length && sum%numberOfParts != 0))
            return;

        boolean[] used = new boolean[numbers.length];
        List<List<Integer>> permutations = new ArrayList<>();
        if(createPartitions(0,4,5,used, numbers, permutations, new ArrayList<>())){
            for(List<Integer> permList:permutations)
                System.out.println("permutations = " + permList.toString());
        }
        else
            System.out.println("No Permutations found ");
    }

    public static boolean createPartitions(int sumSf, int sumNeeded, int numOfParts, boolean[] used, int[] numbers, List<List<Integer>> permutations, List<Integer> singlePermutation){
        if(sumSf == sumNeeded ){
            permutations.add(singlePermutation);
            singlePermutation = new ArrayList<>(); //
            numOfParts -- ;
            sumSf = 0;
            if(numOfParts == 1){
                for(int i=0;i <numbers.length;i++) {
                    if(!used[i])
                        singlePermutation.add(numbers[i]);
                }
                permutations.add(singlePermutation);
                return true;
            }
        }

        for(int i=0;i <numbers.length;i++) {
            if(!used[i] && sumSf+numbers[i] <= sumNeeded){ // constraints, use the number only if it hasnt been used in any of the partitions
                used[i] = true;
                singlePermutation.add(numbers[i]);
                sumSf+=numbers[i];
                if(createPartitions(sumSf,sumNeeded,numOfParts,used,numbers,permutations,singlePermutation))
                    return true;
                //backtrack
                sumSf-=numbers[i];
                singlePermutation.remove(singlePermutation.size()-1);
                used[i] = false;
            }

        }
        return false;

    }
}

/*
* Optimisations that can be done in code
* 1. Now the below statements are not required to be executed, as understand that each stackframe has its value , so for each call
* singlePermuation , sumSf is created, so understand that single variable, need not to be created and it exists in subsequent scope
*    singlePermutation.add(numbers[i]);
      sumSf+=numbers[i];
  2. if (inProgressBucketSum == targetBucketSum) {
      return canPartition(0, arr, used, k - 1, 0, targetBucketSum);
    }
    * This way we can branch off and create a recursion, but keep in mind t start off the recursion right from 0
    * focus on the startingPoint
    *
   3. canPartition(0, arr, used, k - 1, 0, targetBucketSum);
   *  canPartition(i + 1, arr, used, k, inProgressBucketSum + arr[i], targetBucketSum)
   *    startpoint = i, this is one more optimisation to progress and search from subsequent in a given bracket,
   *   because we will be traversing from left to right
* */