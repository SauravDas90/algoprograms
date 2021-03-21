package com.pepcoding.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LexicoGraphicNumber {
    private static List<String> lexicoList = new ArrayList<>();
    private static Set<String> testNUmbers = new HashSet<>();
    public static void main(String[] args){
        int numbers[] = {0,1,2,3,4,5,6,7,8,9};
        testNUmbers.add("1");
        lexicoList.add("1");
        genLexicoGraphic("",10,numbers);
        System.out.println("lexicoList = " + lexicoList);
    }

    public static void genLexicoGraphic(String numSoFar,int max, int[] numbers){
        if(isNumberGreater(numSoFar, max))
            return;

        for(int i =0 ; i < numbers.length ; i++){
            String newNo = numSoFar+numbers[i];
            if(!isNumberGreater(newNo,max) && !testNUmbers.contains(newNo)){
                lexicoList.add(newNo);
                testNUmbers.add(newNo);
                genLexicoGraphic(newNo,max,numbers);
            }
            else if( numbers[i] <= max && !testNUmbers.contains(newNo)){
                String newno2 = String.valueOf(numbers[i]);
               if(! testNUmbers.contains(newno2)){
                   testNUmbers.add(newno2);
                   lexicoList.add(newno2);
                   genLexicoGraphic(newno2,max,numbers);
               }
            }
        }
    }

    private static boolean isNumberGreater(String numSoFar, int max) {
        return Integer.valueOf(numSoFar) > max;
    }
}
