package com.hackerrank.test;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by saurav on 6/6/17.
 */
public class FibonacciModified {

    static BigInteger [] fibo;
    static BigInteger first,second;

    public static void main(String args[])    {

        String t1,t2;
        int n;

        Scanner in = new Scanner(System.in);
        first = new BigInteger(in.next());
        second = new BigInteger(in.next());
        n = in.nextInt();
        fibo = new BigInteger[n];
        fibo[0] = first;
        fibo[1] = second;

        fibonacci(n);

        System.out.print(fibo[n-1]);

    }

    public static BigInteger fibonacci(int n)
    {
        if(n == 1 || n == 2)
            return fibo[n-1];

        if (fibo[n-2] == null || (n-2) <= 2)
            fibonacci(n-1);


        fibo[n-1] = fibo[n-3].add(fibo[n-2].multiply(fibo[n-2]));
        return fibo[n-1];
    }
}
