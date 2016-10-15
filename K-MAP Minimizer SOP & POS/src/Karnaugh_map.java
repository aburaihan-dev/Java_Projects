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

    void kmapSolver_3() {
        Vector<String> solved = new Vector<String>();

        for (int i = 0; i < keep.length / 2; i++) {
            if (keep[i] == true && keep[i + 1] == true && keep[i + 2] == true && keep[i + 3] == true) {
                solved.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 2], bit_string_3[i + 4], bit_string_3[i + 6]));
            }
            if (i == 0 || i == 2) {  // where {0,1,4,5} and {2,3,6,7} group available.
                if (keep[i] == true && keep[i + 1] == true && keep[i + 4] == true && keep[i + 5] == true) {
                    solved.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 1], bit_string_3[i + 4], bit_string_3[i + 5]));
                }
                if (keep[i] == true && keep[i + 1] == true && (keep[i + 4] != true || keep[i + 5] != true)) {
                    solved.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 1]));
                }
                if (keep[i] == true && keep[i + 4] == true && (keep[i + 1] != true || keep[i + 5] != true)) {
                    solved.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 4]));
                }
            }
            if (i == 0 || i == 1) { // {0,2,4,6} and {1,3,5,7} group is available.
                if (keep[i] == true && keep[i + 2] == true && keep[i + 4] == true && keep[i + 6] == true) {
                    solved.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 2], bit_string_3[i + 4], bit_string_3[i + 6]));
                }
                if (keep[i] == true && keep[i + 2] == true && (keep[i + 4] != true || keep[i + 6] != true)) {
                    solved.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 2]));
                }
                if (keep[i] == true && keep[i + 4] == true && (keep[i + 2] != true || keep[i + 6] != true)) {
                    solved.addElement(convertToVar(bit_string_3[i], bit_string_3[i + 4]));
                }
            }
        }

        for (int i = 0; i < solved.size(); i++) {
            if (i == 0) {
                System.out.print(solved.elementAt(i).toString());
            } else {
                System.out.print(" + " + solved.elementAt(i).toString());
            }
        }
    }

    private String convertToVar(String s, String s1, String s2, String s3) {
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
