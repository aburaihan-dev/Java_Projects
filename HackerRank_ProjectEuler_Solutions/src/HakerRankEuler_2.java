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
public class HakerRankEuler_2 {

    /**
     * @param args the command line arguments
     */
    public static long fibonacci(long x) {
        long fib_sum = 0;
        long f0 = 2;
        long f1 = 8;
        long temp;
        fib_sum += f0;
        while (f1 <= x) {
            fib_sum += f1;
            temp = 4 * f1 + f0;
            f0 = f1;
            f1 = temp;
        }

        return fib_sum;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for(int i=0;i<n;i++){
            System.out.println(fibonacci(scan.nextLong()));;
        }
    }

}
