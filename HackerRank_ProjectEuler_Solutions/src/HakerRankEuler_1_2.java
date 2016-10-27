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
public class HakerRankEuler_1_2 {

    /**
     * @param args the command line arguments
     */
    public static int fact(int a) {
        return a * (a + 1);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();
        for (long i = 0; i < n; i++) {
            long x = scan.nextLong();
            long sum=0;
            for(long j=1;j<x;j++){
                if(j%3==0 || j%5==0){
                    sum += j;
                }
            }
            System.out.println("A  = " + sum);
        }
    }

}
