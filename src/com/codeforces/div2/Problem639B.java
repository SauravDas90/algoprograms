package com.codeforces.div2;

import java.util.Scanner;

/**
 * Created by saurav on 27/5/17.
 */




public class Problem639B {

    public static void main(String args []){

        int n,h,d;

        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        d = in.nextInt();
        h= in.nextInt();
        makeTree(n,h,d);

    }

    static void makeTree(int n,int h, int d){

        int even1,even2,i,count;
        even1=even2=i=count=0;
        if(2*h<d  || d<h || (d ==1 && h ==1 && n >2))  // last condition is a special one for (d = 1, h= 1 , 2 ) as there can be only one node
            System.out.println("-1");
        else
        {
           /* for(i=1;i<=(int) Math.ceil(d/2);i++)
            {
                System.out.println(2*(i-1)+1 +" "+  (2*(i-1)+3));
                even1 = (i!= 1? 2 *(i-1):1);
                even2 = (i!=1? (2*(i-1)+2):2);
                System.out.println(even1 + " "+ even2 );
            }


            System.out.println((++even2) +" "+ (++even2));

            for(int t =2* (i-1)+1;t< n-1;t++)
            {
                System.out.println(1+" "+(even2+t-(2* (i-1))));    // while printing never print (""+even2+2) it will concatenate and print , put it in brackets)
            }*/

            for(i =1 ;i <= h;i++,++count)
                System.out.println((i) +" " + (1+i));

            /*System.out.println(1 + " " + (++i));
            ++count;
*/

            for(int k = 1;k<=(d-h) && count < n-1;k++,++count){
                if(k == 1)
                System.out.println(1 +" " + (++i));
                else
                    System.out.println(i +" " + (++i));
            }


            for (;count<n-1;++count)
                if((d-h) > 0)
                    System.out.println(1 +" " + (++i));
                else
                    System.out.println(2 +" " + (++i));

           // System.out.println(1 + " " + (i+1));

        }


    }

}
