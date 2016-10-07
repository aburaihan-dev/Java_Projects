package beginners_problem_practice;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by msrabon on 07-Oct-16.
 * Project Name: URI Online Judge Beginner Problem Solve.
 */
public class Consumption_1014 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double distance = scanner.nextDouble();
        double fuel_spent = scanner.nextDouble();

        DecimalFormat format = new DecimalFormat("#.000");
        System.out.println( format.format(distance/fuel_spent) + " km/l");
    }

}
