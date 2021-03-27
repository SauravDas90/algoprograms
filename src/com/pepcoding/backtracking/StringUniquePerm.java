package com.pepcoding.backtracking;

import java.util.HashMap;
import java.util.Map;

public class StringUniquePerm {

    public static void main(String[] args) {
        String[] elements = {"a","a","b","b"};

       /* boolean[] toolBooleans = new boolean[elements.length];
        printAllPerm(0,4, elements, toolBooleans,"");*/
        // This section is for elements = the no of boxes
        Map<String,Integer> distinct = new HashMap<>();
        for(String elem:elements){
            if(!distinct.containsKey(elem))
                distinct.put(elem,1);  // keep good eye on the way you put the freq of elements
            else
                distinct.put(elem,distinct.get(elem)+1);
        }
       printUniqPerm(0, elements.length, distinct,"");

        System.out.println("===============================================================");
        // this section is for no of boxes > no of elements
        // find no of boxes e.g. 6
        int noOfBoxes = 6;
        // subtract total elements with no of boxes
        int noOfblanks = noOfBoxes - elements.length;
        distinct.put("-",noOfblanks);
        printUniqPermWithSpace(0, noOfBoxes, distinct,"");
    }

    public static void printAllPerm(int usedElements, int totalElements, String[] elements, boolean[] totalbox, String asf){
        if(usedElements == totalElements){
            System.out.println("asf = " + asf);
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            if(!totalbox[i]){
                totalbox[i] = true;
                 printAllPerm(usedElements+1,totalElements,elements,totalbox,asf+elements[i]);
                totalbox[i] = false;
            }
        }
    }

    public static void printUniqPerm(int usedElements, int totalElements, Map<String,Integer> distinct, String asf){
        if(usedElements == totalElements){
            System.out.println("asf = " + asf);
            return;
        }

        for(String entry: distinct.keySet()){
            if(0 == distinct.get(entry)){
                //distinct.remove(entry);
                continue;   // if the distinct elements have been consumed in higher levels, then it cant be used here
                            // using the principle that as we are not using it so we not backtracking
            }
            else
                distinct.put(entry,distinct.get(entry)-1);
                printUniqPerm(usedElements+1, totalElements, distinct, asf+entry);
                //backtrack
                distinct.put(entry,distinct.get(entry)+1);
        }

    }

    public static void printUniqPermWithSpace(int usedElements, int totalElements, Map<String,Integer> distinct, String asf){
        if(usedElements == totalElements){
            System.out.println("asf = " + asf);
            return;
        }

        for(String entry: distinct.keySet()){
            if(0 == distinct.get(entry)){
                //distinct.remove(entry);
                continue;   // if the distinct elements have been consumed in higher levels, then it cant be used here
                // using the principle that as we are not using it so we not backtracking
            }
            else
                distinct.put(entry,distinct.get(entry)-1);
            printUniqPerm(usedElements+1, totalElements, distinct, asf+entry);
            //backtrack
            distinct.put(entry,distinct.get(entry)+1);
        }

    }
}
