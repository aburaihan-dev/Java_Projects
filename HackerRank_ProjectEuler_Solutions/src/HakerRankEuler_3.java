/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 * Template code for HakerRank Euler+
 *
 * @author msrabon
 */
public class HakerRankEuler_3 {

    /**
     * @param args the command line arguments
     */
    public static long prime_factor(long y) {
        long x = y;
        if (x % 2 == 0) {
            x /= 2;
            while (x % 2 == 0) {
                x /= 2;
            }
            x = prime_factor(x);
        } else {
            long i = 3;
            while (i * i <= x) {
                if (x % i == 0) {
                    x /= i;
                    while(x%i ==0){
                        x/=i;
                        i +=2;
                    }
                }
            }
        }

        return x;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println(prime_factor(scan.nextLong()));
        }
    }

}
