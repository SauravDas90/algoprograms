package com.pepcoding.backtracking;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class WordsKSelection2 {

    public static void main(String[] args) {
        Set<String> testMember= new LinkedHashSet<>();
    String elem = "abcabc";
    String uniqElem="";
        for (int i = 0; i < elem.length(); i++) {
            String el = elem.substring(i,i+1);
            if(!testMember.contains(el)){
                testMember.add(el);
                uniqElem+=el;
            }
        }
        int kselection =2;
        generateSelection("",uniqElem,-1,0,kselection);   // keep track of the starting point, because we start at -1 from there move to 0

    }
    public static void generateSelection(String asf,String distinctElem, int strPos,int elemUsed, int totElem){
        if(elemUsed == totElem){
            System.out.println("asf = " + asf);
            return;
        }

        for (int i = strPos+1; i < distinctElem.length(); i++) {
            generateSelection(asf+distinctElem.substring(i,i+1),distinctElem,i,elemUsed+1,totElem);
        }

    }
}
