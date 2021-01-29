package com.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NQueens {

    public static void main(String[] args) {
        List<List<Integer>> res= new ArrayList<>();

        solveNQueens(4,0,res,new ArrayList<>());
        System.out.println(Arrays.asList(res));
        /*List<List<String>> results = new ArrayList<>();

        solveNQueens(0, 4, new ArrayList<>(), results);
        System.out.println(Arrays.asList(results));
*/
    }

    public static void solveNQueens(int n,int row,List<List<Integer>> result, List<Integer> colresult){
        if(row == n){
            result.add(new ArrayList<>(colresult));
            return;
        }
       // else{
            for (int col = 0; col < n; col++) {
                colresult.add(col);
                if(isValid(colresult)){
                    solveNQueens(n,row+1,result,colresult);
                }

                colresult.remove(row);
               // colresult.remove(row);
            }
        //s}

    }

    public static boolean isValid(List<Integer>colPlacements){
       /* int size = colresult.size()-1;
        int elem = colresult.get(size);
        for(int j = 0; j<size; j++){
            int diff = Math.abs(colresult.get(j)-elem);
            if(diff == 0 || diff == Math.abs(size-j)){
                return false;
            }
        }
        return true;*/

        int rowWeAreValidatingOn = colPlacements.size() - 1;


        for (int ithQueenRow = 0; ithQueenRow < rowWeAreValidatingOn; ithQueenRow++) {


            int absoluteColumnDistance = Math.abs(colPlacements.get(ithQueenRow) - colPlacements.get(rowWeAreValidatingOn));


            int rowDistance = rowWeAreValidatingOn - ithQueenRow;
            if (absoluteColumnDistance == 0 || absoluteColumnDistance == rowDistance) {
                return false;
            }
        }

        return true;
    }
}
