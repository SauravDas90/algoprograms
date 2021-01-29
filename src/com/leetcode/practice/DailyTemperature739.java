package com.leetcode.practice;

import java.util.Arrays;
import java.util.Stack;

/*
* Solution by monotonic stack, using NGR*/
public class DailyTemperature739 {
    public static int[] nextWarmerDay(int[] temp){
        int[] nextSmallerDays = new int[temp.length];
        Stack<KeyValue> greaterStack  = new Stack<>();
        for (int i = temp.length-1; i>=0 ; i--) {
            if(greaterStack.isEmpty())
                nextSmallerDays[i] = 0;
            else{
                if(greaterStack.peek().getTemp() > temp[i]){
                    nextSmallerDays[i] = greaterStack.peek().getIdx() - i;  // assuming i will be on the left side
                }
                else{
                    while(!greaterStack.isEmpty() && greaterStack.peek().getTemp() <= temp[i]){
                        greaterStack.pop();
                    }
                    if(greaterStack.isEmpty())
                        nextSmallerDays[i] = 0;
                    else
                        nextSmallerDays[i] = greaterStack.peek().getIdx() - i;
                }
            }
            greaterStack.push(new KeyValue(temp[i],i));
        }
        return nextSmallerDays;
    }
    public static void main(String[] args) {
        int[] temp ={73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(nextWarmerDay(temp)));

        //Prior to Java 8
       // System.out.println(Arrays.toString(intArray));
       // System.out.println(Arrays.toString(strArray));

        // In Java 8 we have lambda expressions
        //Arrays.stream(intArray).forEach(System.out::println);
        //Arrays.stream(strArray).forEach(System.out::println);

        // In case we need to use List to add elements at the front DeQue or LinkedList is good,
        // as it will not be an array backed, so adding element at front wont shift elements
        // But GC timings might get worse,as the Heap memory will have values scattered
    }

}
class KeyValue{
    int temp, idx;
    KeyValue(int temp, int idx){
        this.temp = temp;
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }

    public int getTemp() {
        return temp;
    }
}

// one alternative solution
// while (!deque.isEmpty() && price >= deque.peekLast().val) {
//            deque.pollLast();
//        }
//        int res = deque.isEmpty() ? -1 : deque.peekLast().index;
//        deque.offerLast(new Element(count,price));
//        count++;
//        return deque.peekLast().index - res;