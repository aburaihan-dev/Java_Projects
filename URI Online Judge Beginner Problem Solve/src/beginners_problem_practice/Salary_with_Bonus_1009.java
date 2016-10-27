package beginners_problem_practice;

import java.text.DecimalFormat;
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

        double salary_finale = salary + (sales * .15) ;

        /**
         * link given below solved the problem.
         * http://stackoverflow.com/questions/8819842/best-way-to-format-a-double-value-to-2-decimal-places
         */
        DecimalFormat format = new DecimalFormat("#.00");
        System.out.println("TOTAL = R$ " + format.format(salary_finale));
    }

}
