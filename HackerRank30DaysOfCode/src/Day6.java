import java.util.Scanner;

/**
 * Created by msrabon on 04-Oct-16.
 * Project Name: HackerRank30DaysOfCode.
 */
public class Day6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String[] str = new String[n];
        String str_odd = "";
        String str_evn = "";
        scan.nextLine(); // again clearing dummy input from input buffer memory.
        for (int i = 0; i < n ; i++) {
            str[i] = scan.nextLine();
        }

        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < str[i].length() ; j++) {
                if (j%2 == 0){
                    str_evn += str[i].charAt(j);
                } else {
                    str_odd += str[i].charAt(j);
                }
            }
            System.out.println(str_evn + " " + str_odd);
            str_evn = "";
            str_odd = "";
        }
    }
}
