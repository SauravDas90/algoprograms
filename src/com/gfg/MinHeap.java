package com.gfg;



public class MinHeap {

   private static int tree[];
    public MinHeap(int a[]) {
        tree =a;
    }

    public static void heapify(int elem){
        //basic condfition, when inserting first time into heap
        if(tree[0] == 0) {
            tree[0] = elem;
            return;
        }
        int i = 0;
        while (elem > tree[i]){
            if(tree[2*i+1] == 0)
                tree[2*i+1] = elem;
            else if(tree[2*(i+1)] == 0)
                tree[2*(i+1)] = elem;
            i++;
        }

    }
}
