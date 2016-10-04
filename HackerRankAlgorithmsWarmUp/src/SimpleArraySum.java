import java.util.Scanner;

/**
 * Created by msrabon on 04-Oct-16.
 * Project Name: HackerRankAlgorithmsWarmUp.
 */
public class SimpleArraySum {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long sum =0;
        while(n-- > 0){
            sum += scan.nextLong();
        }
        System.out.println(sum);
    }
}
