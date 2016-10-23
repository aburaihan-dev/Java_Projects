import java.util.Scanner;

/**
 * Created by msrabon on 04-Oct-16.
 * Project Name: HackerRank30DaysOfCode.
 */
public class day4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Person person_A;
        int n = input.nextInt();
        while (n-- > 0){
            person_A = new Person(input.nextInt());
            person_A.amIOld();
            person_A.yearPasses();
            person_A.amIOld();
        }
    }
}
