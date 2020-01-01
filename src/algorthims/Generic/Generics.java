package algorthims.Generic;

import java.util.Scanner;

/**
 * Write a single generic function named printArray; this function
 * must take an array of generic elements as a parameter,
 * Your printArray function should print each element of its generic array parameter on a new line.
 * Constraints:
 * You must have exactly 1 function named printArray.
 */

public class Generics {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of integer: ");
        int n = scanner.nextInt();
        Integer[] intArray = new Integer[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the number:");
            intArray[i] = scanner.nextInt();
        }

        System.out.print("Enter the number of strings, you want: ");
        n = scanner.nextInt();
        String[] stringArray = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the string: ");
            stringArray[i] = scanner.next();
        }

        Printer<Integer> intPrinter = new Printer<Integer>();
        Printer<String> stringPrinter = new Printer<String>();
        intPrinter.printArray( intArray  );
        stringPrinter.printArray( stringArray );

        if(Printer.class.getDeclaredMethods().length > 1){
            System.out.println("The Printer class should only have 1 method named printArray.");
        }

    }
}
