package beginners_problem_practice;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by msrabon on 07-Oct-16.
 * Project Name: URI Online Judge Beginner Problem Solve.
 */
public class Distance_Between_Two_Points_1015 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double x1, y1;
        double x2, y2;
        x1 = scan.nextDouble();
        y1 = scan.nextDouble();
        x2 = scan.nextDouble();
        y2 = scan.nextDouble();
        double dist = Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
        DecimalFormat format = new DecimalFormat("#.0000");
        System.out.println(format.format(dist));
    }
}
