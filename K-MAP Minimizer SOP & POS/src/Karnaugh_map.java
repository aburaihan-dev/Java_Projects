import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by msrabon on 12-Oct-16.
 * Project Name: K-MAP Minimizer SOP & POS.
 */
public class Karnaugh_map {

    int[][] k_map = new int[2][4];

    public void showKmapGrid() {
        for (int i = 0; i < k_map.length; i++) {

            for (int j = 0; j < k_map[i].length; j++) {
                System.out.print(k_map[i][j] + " ");
            }

            System.out.println();

        }
    }

    public void insertToKmap() {
        Scanner scan = new Scanner(System.in);
        int x;
        int index = 0;

        System.out.println("Enter '-1' to stop input.");
        System.out.print("Enter 1's : ");

        while ((x = scan.nextInt()) != -1) {
            if (x > 3 && index == 0) {
                index++;
            } else if (x <= 3 && index > 0) {
                index--;
            }
            k_map[index][x % 4] = 1;
            System.out.print("Enter 1's : ");
        }
    }

    public void solveKmap(){
        //Solution Starts here.
        for (int i = 0; i < k_map.length ; i++) {

        }
    }

}
