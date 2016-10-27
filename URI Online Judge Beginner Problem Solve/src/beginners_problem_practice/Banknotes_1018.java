package beginners_problem_practice;

import java.util.Scanner;

/**
 * Created by msrabon on 07-Oct-16.
 * Project Name: URI Online Judge Beginner Problem Solve.
 */
public class Banknotes_1018 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        long amount = scan.nextInt();
        System.out.println(amount);
        if (amount >= 100) {
            System.out.println((amount / 100) + " nota(s) de R$ 100,00");
            amount %= 100;
        } else {
            System.out.println("0 nota(s) de R$ 100,00");
        }

        if (amount >= 50) {
            System.out.println(amount / 50 + " nota(s) de R$ 50,00");
            amount %= 50;
        } else {
            System.out.println("0 nota(s) de R$ 50,00");
        }

        if (amount >= 20) {
            System.out.println(amount / 20 + " nota(s) de R$ 20,00");
            amount %= 20;
        }else {
            System.out.println("0 nota(s) de R$ 20,00");
        }

        if (amount >= 10) {
            System.out.println(amount / 10 + " nota(s) de R$ 10,00");
            amount %= 10;
        } else {
            System.out.println("0 nota(s) de R$ 10,00");
        }
        if (amount >= 5) {
            System.out.println(amount / 5 + " nota(s) de R$ 5,00");
            amount %= 5;
        }else{
            System.out.println("0 nota(s) de R$ 5,00");
        }
        if (amount >= 2) {
            System.out.println(amount / 2 + " nota(s) de R$ 2,00");
            amount %= 2;
        }else {
            System.out.println("0 nota(s) de R$ 2,00");
        }
        if (amount >= 1) {
            System.out.println(amount / 1 + " nota(s) de R$ 1,00");
//            amount %= 1;
        }else {
            System.out.println("0 nota(s) de R$ 1,00");
        }

    }

}
