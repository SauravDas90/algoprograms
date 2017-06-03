package com.hackerrank.test;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class SolutionImp1{

	/*public static void main(String[] args)throws Exception {
		BigInteger b= new BigInteger("1");
		int num;
		
		Scanner in = new Scanner(System.in);
		num = in.nextInt();
		
		for(int k=1;k<=num;k++)
		{
			b=b.multiply(BigInteger.valueOf(k));
		}
		
		
		System.out.println("Factorial value is = " + b);
		
		in.close();
		// TODO Auto-generated method stub

	}
*/

	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		int n,m,a[],b[],x[],y[];


		Scanner in = new Scanner (System.in);
		n = in.nextInt();
		a = new int[n];
		for(int i=0;i<n;i++)
			a[i] = in.nextInt();
		m = in.nextInt();
		b= new int[m];
		for(int i=0;i<m;i++)
			b[i] = in.nextInt();



		Arrays.sort(a);
		Arrays.sort(b);

        System.out.println(a[0] + "\t" + a[a.length-1]);

        System.out.println(b[0] + "\t" + b[b.length-1]);
		x = new int[101];
		for(int r=0;r<b.length;r++)
		{
			int diff = b[b.length-1]-b[r];
			x[diff]++;
		}

		y = new int[101];
		for(int r=0;r<a.length;r++)
		{
			int diff = a[a.length-1]-a[r];
			y[diff]++;
		}

		for(int r=100;r>=0;r--)
		{

			if(x[r]-y[r] != 0)
                System.out.print(Integer.valueOf(b[b.length-1]-r)+"\t");
		}


// In this solution the issue was with the boundary value, so please ensure that it doesnt happens. (Also note the output index achieved
	}
}
