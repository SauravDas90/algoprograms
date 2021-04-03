package com.leetcode.practice.binsearch;

public class InfiniteSortedArray {

    public static void main(String[] args) {
        searchInfiniteSortedArray(5);
    }

    public static void searchInfiniteSortedArray(int k){
        //Infinite sorted array, hence only the, start and end is start +1
        long start = 0L;
        long end = 1L;

        while(end < k)
        {
            start = end;
            end*=2;
        }

        //once the range is obtained, search like a binary search
        int mid = -1;
        while(start <= end){
            mid= (int) (start + (end-start)/2);

            if((mid+1) == k)
            {
                System.out.println("Find the value at index.."+ k+"..mid.."+ mid);
                break; // as its inifinte sorted array, assuming that value will always be found
            }
            else if(k > (mid+1)){
                start = mid+1;
            }
            else{
                end = mid-1;
            }

        }

    } // remember that the sorted array should be found in time complexity o9 log n)
}
