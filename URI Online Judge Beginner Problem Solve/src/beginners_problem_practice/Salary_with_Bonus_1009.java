package beginners_problem_practice;

import java.util.Scanner;

/**
 * Created by msrabon on 07-Oct-16.
 * Project Name: URI Online Judge Beginner Problem Solve.
 */
public class Salary_with_Bonus_1009 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.nextLine();
        double salary = in.nextDouble();
        double sales = in.nextDouble();

        double salary_finale = salary + sales * .15 ;

        System.out.printf(" %lf",salary_finale);

//        System.out.format("TOTAL = R$ %.2d",salary_finale);
    }

}
