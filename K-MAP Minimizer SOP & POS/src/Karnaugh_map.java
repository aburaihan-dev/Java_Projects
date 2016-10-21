import java.util.Scanner;
import java.util.Vector;

/**
 * Created by msrabon on 12-Oct-16.
 * Project Name: K-MAP Minimizer SOP & POS.
 */
public class Karnaugh_map {

    final String[] bit_string_3 = {"000", "001", "010", "011", "100", "101", "110", "111"};
    final String[] bit_string_4 = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    boolean[] keep = new boolean[16];
    final int[][] kmap_3 = new int[2][4];
    final int[][] kmap_4 = new int[4][4];


    void initiate_Kmap_3() {
        Scanner scan = new Scanner(System.in);
        int x;
        int index = 0;
        System.out.print("Enter 1's Location (insert '-1' to stop input): ");
        while ((x = scan.nextInt()) != -1) {
            if (x > 3 && index == 0) {
                index++;
            }
            if (x <= 3 && index == 1) {
                index--;
            }
            if (x < 8) {
                keep[x] = true;
                kmap_3[index][x % 4] = 1;
            } else {
                System.out.println("Invalid location.");
            }
            System.out.print("Enter 1's Location (insert '-1' to stop input): ");
        }

        index = 0;
        System.out.print("Enter Don't care Location (insert '-1' to stop input): ");
        while ((x = scan.nextInt()) != -1) {
            if (x > 3 && index == 0) {
                index++;
            }
            if (x <= 3 && index == 1) {
                index--;
            }
            if (kmap_3[index][x % 4] == 1 && x < 8) {
                System.out.println("This location is occupied by 1's.\nTry again.");
            }
            if (x < 8) {
                kmap_3[index][x % 4] = 2;
            } else {
                System.out.println("Invalid location.");
            }
            System.out.print("Enter Don't care Location (insert '-1' to stop input): ");
        }

    }

    void initiate_Kmap_4() {
        Scanner scan = new Scanner(System.in);
        int x;
        int index = 0;
        System.out.print("Enter 1's Location (insert '-1' to stop input): ");
        while ((x = scan.nextInt()) != -1) {
            if (x > 3 && index == 0) {
                index++;
            }
            if (x <= 3 && index == 1) {
                index--;
            }
            if (x < 8) {
                keep[x] = true;
                kmap_3[index][x % 4] = 1;
            } else {
                System.out.println("Invalid location.");
            }
            System.out.print("Enter 1's Location (insert '-1' to stop input): ");
        }

        index = 0;
        System.out.print("Enter Don't care Location (insert '-1' to stop input): ");
        while ((x = scan.nextInt()) != -1) {
            if (x > 3 && index == 0) {
                index++;
            }
            if (x <= 3 && index == 1) {
                index--;
            }
            if (kmap_3[index][x % 4] == 1 && x < 8) {
                System.out.println("This location is occupied by 1's.\nTry again.");
            }
            if (x < 8) {
                kmap_3[index][x % 4] = 2;
            } else {
                System.out.println("Invalid location.");
            }
            System.out.print("Enter Don't care Location (insert '-1' to stop input): ");
        }

    }

    void k_mapSolver_three() { // K-map 3 variable soluion.
        Vector solutions = new Vector();
        if (keep[0] && keep[1] && keep[2] && keep[3]) { // {0,1,2,3}
            solutions.addElement(convertToVar(bit_string_3[0], bit_string_3[3]));
        } else if (keep[4] && keep[5] && keep[6] && keep[7]) { // {4,5,6,7}
            solutions.addElement(convertToVar(bit_string_3[4], bit_string_3[7]));
        }

        for (int i = 0; i < 3; i++) {
            if ((i == 0 || i == 1) && keep[i] && keep[i + 2] && keep[i + 4] && keep[i + 6]) { // {0,2,4,6} and {1,3,5,7}
                solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 6]));
            }
            if (i != 1 && keep[i] && keep[i + 1] && keep[i + 4] && keep[i + 5]) { //{0,1,4,5} , {2,3,6,7}
                solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 5]));
            }
        }

        for (int i = 0; i < 3; i++) {

            if (i == 0 || i == 1) {
                if (keep[i] && keep[i + 2] && keep[i + 4] && !keep[i + 6]) {
                    solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 2]));
                    solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 4]));
                } else if (keep[i] && keep[i + 2] && !keep[i + 4] && keep[i + 6]) {
                    solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 2]));
                    solutions.addElement(convertToVar(bit_string_3[i + 2], bit_string_3[i + 6]));
                } else if (keep[i] && !keep[i + 2] && keep[i + 4] && keep[i + 6]) {
                    solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 4]));
                    solutions.addElement(convertToVar(bit_string_3[i + 4], bit_string_3[i + 6]));
                } else if (!keep[i] && keep[i + 2] && keep[i + 4] && keep[i + 6]) {
                    solutions.addElement(convertToVar(bit_string_3[i + 2], bit_string_3[i + 6]));
                    solutions.addElement(convertToVar(bit_string_3[i + 4], bit_string_3[i + 6]));
                }
            }

            if (i != 1 && keep[i] && keep[i + 1] && keep[i + 4] && !keep[i + 5]) {
                solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 1]));
                solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 4]));
            } else if (i != 1 && keep[i] && keep[i + 1] && keep[i + 5] && !keep[i + 4]) {
                solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 1]));
                solutions.addElement(convertToVar(bit_string_3[i + 1], bit_string_3[i + 5]));
            } else if (i != 1 && keep[i] && keep[i + 4] && keep[i + 5] && !keep[i + 1]) {
                solutions.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 4]));
                solutions.addElement(convertToVar(bit_string_3[i + 4], bit_string_3[i + 5]));
            } else if (i != 1 && keep[i + 1] && keep[i + 4] && keep[i + 5] && !keep[i]) {
                solutions.addElement(convertToVar(bit_string_3[i + 1], bit_string_3[i + 5]));
                solutions.addElement(convertToVar(bit_string_3[i + 4], bit_string_3[i + 5]));
            }
        }

    }

    private String convertToVar(String s, String s3) {
        String solution = "";

        if (String.valueOf(s.charAt(0)).equals(String.valueOf(s3.charAt(0)))) {
            if (String.valueOf(s.charAt(0)).equals("0")) {
                solution += "A'";
            } else {
                solution += "A";
            }
        }
        if (String.valueOf(s.charAt(1)).equals(String.valueOf(s3.charAt(1)))) {
            if (String.valueOf(s.charAt(1)).equals("0")) {
                solution += "B'";
            } else {
                solution += "B";
            }
        }
        if (String.valueOf(s.charAt(2)).equals(String.valueOf(s3.charAt(2)))) {
            if (String.valueOf(s.charAt(2)).equals("0")) {
                solution += "C'";
            } else {
                solution += "C";
            }
        }
        if (s.length() > 3) {
            if (String.valueOf(s.charAt(3)).equals(String.valueOf(s3.charAt(3)))) {
                if (String.valueOf(s.charAt(2)).equals("0")) {
                    solution += "D'";
                } else {
                    solution += "D";
                }
            }
        }
        return solution;
    }

}
