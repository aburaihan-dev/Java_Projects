import java.util.Scanner;

/**
 * Created by msrabon on 04-Oct-16.
 * Project Name: HackerRank30DaysOfCode.
 */
public class Day5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i=1;i<=10;i++){
            System.out.println(n + " x " + i + " = " + n*i);
        }
    }
}
