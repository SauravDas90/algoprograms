package com.gfg.ds.arrays.rotation;

import java.util.*;


public class BlockSwapRotation {
    public static void main (String[] args)
    {
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        leftRotate(arr,0, 2, 7);
        printArray(arr, 7);
    }
    public static void leftRotate(int arr[], int i,
                                  int d, int n)

    {
		/* Return If number of elements to be rotated
		is zero or equal to array size */
        if(d == 0 || d == n)
            return;

		/*If number of elements to be rotated
		is exactly half of array size */
        if(n - d == d)
        {
            swap(arr, i, n - d + i, d);
            return;
        }

        /* If A is shorter*/
        if(d < n - d)
        {
            swap(arr, i, n - d + i, d);
            leftRotate(arr, i, d, n - d);
        }
        else /* If B is shorter*/
        {
            swap(arr, i, d, n - d);
            leftRotate(arr, n - d + i, 2 * d - n, d); /*This is tricky*/
        }
    }

    /*UTILITY FUNCTIONS*/
    /* function to print an array */
    private  static void printArray(int arr[], int size)
    {
        int i;
        for(i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    /*This function swaps d elements
    starting at index fi with d elements
    starting at index si */
    private static void swap(int arr[], int fi,
                            int si, int d)
    {
        int i, temp;
        for(i = 0; i < d; i++)
        {
            temp = arr[fi + i];
            arr[fi + i] = arr[si + i];
            arr[si + i] = temp;
        }
    }


}


