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
public class day1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int i = 4;
        double d = 4.0;
        String s = "HackerRank ";

        Scanner scan = new Scanner(System.in);

        int x = scan.nextInt();
        double y = scan.nextDouble();
        String q = scan.next().toString();

        System.out.println(i+x);
        System.out.println(y+d);
        System.out.println(s+q);
    }

}
