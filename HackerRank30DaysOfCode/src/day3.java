import java.util.Scanner;

/**
 * Created by msrabon on 30-Sep-16.
 */
public class day3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.close();
        String ans="";

        // if 'n' is NOT evenly divisible by 2 (i.e.: n is odd)
        if(n%2==1){
            ans = "Weird";
        }
        else if (n>=2 && n<=5 && n%2==0){
            // Complete the code
            ans = "Not Weird";
        }else if (n>=6 && n<=20 && n%2==0){
            ans = "Weird";
        }else{
            ans = "Not Weird";
        }
        System.out.println(ans);
    }
}
