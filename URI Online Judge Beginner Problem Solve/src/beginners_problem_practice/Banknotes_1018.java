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
        if(amount > 100){
            System.out.println(amount/100);
            amount %= 100;
        }
        if(amount > 50){
            System.out.println(amount/50);
            amount %= 50;
        }
        if(amount > 20){
            System.out.println(amount/20);
            amount %= 20;
        }
        if(amount > 100){
            System.out.println(amount/100);
            amount %= 100;
        }
        if(amount > 10){
            System.out.println(amount/10);
            amount %= 10;
        }
        if(amount > 5){
            System.out.println(amount/5);
            amount %= 5;
        }
        if(amount > 2){
            System.out.println(amount/2);
            amount %= 2;
        }
        if(amount > 1){
            System.out.println(amount/1);
//            amount %= 1;
        }

    }

}
