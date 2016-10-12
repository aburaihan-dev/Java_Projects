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

    public void solveKmap() {
        //Solution Starts here.

        // 3 variable kmap solution.
        boolean[] pair_h1 = new boolean[k_map[0].length];
        boolean[] pair_h2 = new boolean[k_map[0].length];
        boolean[] pair_v = new boolean[k_map[0].length];

        for (int i = 0; i < k_map[0].length; i++) {
            if (k_map[0][i] == 1 && k_map[1][i] == 1) {
                pair_v[i] = true;
            }
            if (k_map[0][i] == 1 && k_map[0][i + 1] == 1 && i < k_map[0].length) {
                pair_h1[i] = true;
            }
            if (k_map[1][i] == 1 && k_map[1][i + 1] == 1 && i < k_map[0].length) {
                pair_h2[i] = true;
            }
        }

        System.out.print("1st lane Horizontal paris: ");
        for (int i = 0; i < pair_h1.length; i++) {
            System.out.print(pair_h1[i] + " ");
        }
        System.out.println();
        System.out.print("2nd lane Horizontal paris: ");
        for (int i = 0; i < pair_h1.length; i++) {
            System.out.print(pair_h1[i] + " ");
        }
        System.out.println();
        System.out.print("Vertical Pairs: ");
        for (int i = 0; i < pair_v.length; i++) {
            System.out.print(pair_v[i] + " ");
        }
    }

}
