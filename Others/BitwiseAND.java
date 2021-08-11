package Others;

import java.util.Scanner;

public class BitwiseAND {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Enter the size: ");
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {

            System.out.print("Enter the two integers seperated by space: ");
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);


            System.out.println(bitwiseAnD(n, k));
        }

        scanner.close();
    }

    private static int bitwiseAnD(int n, int k) {

        int max_possible = 0;

        for(int num = 1; num<n+1; num++){

            for(int secondNum = num+1; secondNum<n+1; secondNum++){

                int bitwise = (num & secondNum);
                if(bitwise<k){

                    if(bitwise>max_possible){
                        max_possible = bitwise;
                    }
                }

            }

        }

        return max_possible;
    }
}
