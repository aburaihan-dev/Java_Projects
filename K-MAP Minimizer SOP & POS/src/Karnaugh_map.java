import java.util.Scanner;

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

    void kmapSolver_3() {
        String solve = "";
        for (int i = 0; i < keep.length / 4; i++) {
            if (keep[i] == true && keep[i + 1] == true) {
                solve += convertToVar(bit_string_3[i], bit_string_3[i + 1]);
            }
            if (keep[i] == true && keep[i + 4] == true) {
                solve += (" + " + convertToVar(bit_string_3[i], bit_string_3[i + 4]));
            }
            System.out.println(solve);
        }
    }

    private String convertToVar(String s, String s1) {
        String solution = "";
        if (s.charAt(0) == s1.charAt(0)) {
//            System.out.println(s.charAt(0) + " " + s1.charAt(0));
            if (String.valueOf(s.charAt(0)).equals("0")) {
                solution += "A'";
            } else {
                solution += "A";
            }
            if (s.charAt(1) == s1.charAt(1)) {
                if (String.valueOf(s.charAt(1)).equals("0")) {
                    solution += "B'";
                } else {
                    solution += "B";
                }
            } else {
                if (s.charAt(2) == s1.charAt(2)) {
                    if (String.valueOf(s.charAt(2)).equals("0")) {
                        solution += "C'";
                    } else {
                        solution += "C";
                    }
                }
            }
        } else {
            if (s.charAt(1) == s1.charAt(1)) {
                if (String.valueOf(s.charAt(1)).equals("0")) {
                    solution += "B'";
                } else {
                    solution += "B";
                }
                if (String.valueOf(s.charAt(2)).equals("0")) {
                    solution += "C'";
                } else {
                    solution += "C";
                }
            }
        }

        return solution;
    }

    void kmapSolver_4() {

    }

}
