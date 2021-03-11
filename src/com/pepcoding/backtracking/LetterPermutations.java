package com.pepcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LetterPermutations {

    static  String[] mapping = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    
    public static void main(String[] args) {
        String ip = "203";
        List<String> totalpermutations = new ArrayList<>();
        getLetterPermute(ip,"",mapping,totalpermutations);
        System.out.println("totalpermutations.toString() = " + totalpermutations.toString());

    }

    public static void getLetterPermute(String ip, String op, String[] mapping, List<String> permutations){

        if(0 == ip.length()){
            permutations.add(op);
            return;
        }
        String fl = ip.substring(0,1);
        int idx = Integer.valueOf(fl);
        String choice = mapping[idx];
        ip = ip.substring(1);

        if(choice == "") {
            getLetterPermute(ip, op, mapping, permutations);
        }
        else {
            for (int i = 0; i < choice.length(); i++) {
                op = op + choice.substring(i, i + 1);
                getLetterPermute(ip, op, mapping, permutations);
                op = op.substring(0, op.length() - 1);
            }
        }

    }
}
/*
missed the edge case when string is empty
 */