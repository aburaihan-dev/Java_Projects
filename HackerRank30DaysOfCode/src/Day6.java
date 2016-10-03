import java.util.Scanner;

/**
 * Created by msrabon on 04-Oct-16.
 * Project Name: HackerRank30DaysOfCode.
 */
public class Day6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str;
        String str_odd = "";
        String str_evn = "";
        int n = scan.nextInt();
        scan.nextLine(); // again clearing dummy input in the input buffer.
        while (n-- > 0) {
            str = scan.nextLine();
            for (int i = 0; i < str.length(); i++) {
                if (i % 2 == 0) {
                    str_evn += str.charAt(i);
                }else {
                    str_odd += str.charAt(i);
                }
            }
            System.out.println(str_evn + "  " + str_odd);
            str_evn = "";
            str_odd = "";
        }
    }
}
