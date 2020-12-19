package Algorithms.AtCoderAndGeeksForGeeks;

import java.util.Scanner;

public class OneQuadrillionandOneDalmatians {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long input = scanner.nextLong();

        StringBuilder sbr = new StringBuilder();
        while ( input > 0 ){
            input -= 1;
            sbr.append( (char) ( 'a' + input % 26 ));
            input /= 26;
        }

        System.out.println( sbr.reverse().toString());
    }
}
