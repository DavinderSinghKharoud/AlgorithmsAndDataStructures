package algorthims.AtCoderAndGeeksForGeeks;

import java.util.Scanner;

public class RedScarf {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] array = new int[len];

        int allXor = 0;
        for (int index = 0; index < len; index++) {
            array[index] = scanner.nextInt();
            allXor = allXor ^ array[index];
        }

        for (int index = 0; index < len; index++) {
            System.out.println( array[index] ^ allXor );
        }
    }
}
