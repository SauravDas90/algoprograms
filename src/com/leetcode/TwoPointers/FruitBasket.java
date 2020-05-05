package com.leetcode.TwoPointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FruitBasket {

    public static void main(String[] args) {
        //int arr[] = {3,3,3,1,2};
       // int arr[] = {3,3,3,1,2,1,1,2,3,3,4,3,3,3,3,3,3,5,5,7,3,3,4};
        int arr[] = {3,3,3,1,2,1,1,2,3,3,4};

         totalFruit(arr);
    }

    public static int totalFruit(int[] tree) {
        int len = tree.length;

        if(len<=2)
            return len;

        int maxFruitLength = 2;
        int maxFruitsInBasket = 0;
        // Define a HashMap of length 2;
        Map<Integer,Integer> windowFruits = new HashMap<>();


        windowFruits.put(tree[0],0);
        windowFruits.putIfAbsent(tree[1],1);  // this could be null if the first 2 elements are same

        for(int i =2 ; i<len;i++){

            if(windowFruits.containsKey(tree[i])){
                maxFruitLength++;
                // update the index
                //  windowFruits.put(tree[i],i);
            }

            else{
                if(windowFruits.size() <= 1){   // base condition if the nos get repeated
                    maxFruitLength++;
                    windowFruits.put(tree[i],i);
                }
                else{
                    int keyNotTobeRemoved = tree[i-1];
                    windowFruits.entrySet().removeIf( entry -> (keyNotTobeRemoved != entry.getKey()));
                    windowFruits.put(tree[i],i);
                    maxFruitsInBasket = Math.max(maxFruitsInBasket,maxFruitLength);
                    maxFruitLength =(i-windowFruits.get(keyNotTobeRemoved))+1;
                }
            }

        }

        // this is for conditions, when the max ends at last index;
        maxFruitsInBasket = Math.max(maxFruitsInBasket,maxFruitLength);
        return maxFruitsInBasket;

    }
}
