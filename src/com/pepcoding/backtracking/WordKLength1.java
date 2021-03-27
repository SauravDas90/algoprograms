package com.pepcoding.backtracking;

import java.util.LinkedHashSet;
import java.util.Set;

public class WordKLength1 {
    public static void main(String[] args) {
        Set<String> testContains= new LinkedHashSet<>();
        String abcd="sbcdscd";
        String uniqueElem= "";
        for (int i = 0; i < abcd.length(); i++) {
            String s = abcd.substring(i,i+1);
            if(!testContains.contains(s)){
                testContains.add(s);
                uniqueElem+=s;
            }
        }
        int k =1;
        boolean[] used = new boolean[uniqueElem.length()];
        generateKLength("",0,k,uniqueElem,used);
    }

    public static void generateKLength(String asf,int levelused, int totalLevels, String uniqueElem, boolean[] used){
        if(levelused == totalLevels){
            System.out.println("asf = " + asf);
            return;
        }

        for (int i = 0; i < uniqueElem.length(); i++) {
            if(!used[i]){
                used[i] = true;
                generateKLength(asf+uniqueElem.substring(i,i+1),levelused+1, totalLevels,uniqueElem, used);
                used[i] = false;
            }
        }
    }
}
