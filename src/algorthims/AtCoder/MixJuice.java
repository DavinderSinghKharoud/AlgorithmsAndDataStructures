package algorthims.AtCoder;

import java.util.Arrays;
import java.util.Scanner;

public class MixJuice {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int choose = scanner.nextInt();
        int[] prices = new int[len];

        for(int index = 0; index < len; index++ ){
            prices[index] = scanner.nextInt();
        }

        Arrays.sort(prices);
        int sum = 0;
        for(int index = 0; index < choose; index++ ){
            sum += prices[index];
        }
        System.out.println(sum);
        scanner.close();
    }
}
