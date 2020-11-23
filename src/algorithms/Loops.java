package algorithms;

import java.util.Scanner;

/**
 * Objective
 * In this challenge,
 * we're going to use loops to help us do some simple math. Check out the Tutorial tab to learn more.
 *
 * Task
 * Given an integer, , print its first  multiples. Each multiple  (where )
 * should be printed on a new line in the form: n x i = result.
 */

public class Loops {

    public static void main(String[] args) {

        System.out.print("Enter a number: ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        for(int index=1; index<11; index++){
            System.out.println(num + " x " + index + " = " + (num*index));
        }
    }

}
