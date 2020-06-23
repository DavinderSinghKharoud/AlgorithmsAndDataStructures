package algorthims.AtCoder;



import java.util.Scanner;

public class Replacing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );
        int len = scanner.nextInt();
        int[] arr = new int[100001];
        long total = 0;
        for (int count = 0; count < len; count++) {
            int num = scanner.nextInt();
            arr[num]++;
            total += num;
        }

        int queries = scanner.nextInt();
        for (int count = 0; count < queries; count++) {
            int num = scanner.nextInt();
            int replace = scanner.nextInt();
            if( arr[num] != 0 ){
                arr[replace] += arr[num];
                total += ( replace - num ) *  arr[num];
                arr[num] = 0;
            }
            System.out.println(total);
        }
        scanner.close();
    }
}
