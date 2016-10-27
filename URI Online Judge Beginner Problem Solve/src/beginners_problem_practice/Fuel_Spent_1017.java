package beginners_problem_practice;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by msrabon on 07-Oct-16.
 * Project Name: URI Online Judge Beginner Problem Solve.
 */
public class Fuel_Spent_1017 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        final double eff = 12.00;
        double hour = scan.nextDouble();
        double dist = scan.nextDouble();

        DecimalFormat format = new DecimalFormat("#.000");

        System.out.println(format.format(hour*dist /eff));
    }

}
