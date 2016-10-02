/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author msrabon
 */
public class HakerRankEuler_1 {

    /**
     * @param args the command line arguments
     */
    public static long fact(long a) {
        return a * (a + 1);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        long n = scan.nextInt();
        for (long i = 0; i < n; i++) {
            long x = scan.nextInt();
            long y = x - 1;
            long a = y / 3;
            long b = y / 5;
            long c = y / 15;

            System.out.println((3 * fact(a) + 5 * fact(b) - 15 * fact(c)) / 2);
//            int sum=0;
//            for(int j=1;j<x;j++){
//                if(j%3==0 || j%5==0){
//                    sum += j;
//                }
//            }
//            System.out.println(sum);
        }
    }

}
