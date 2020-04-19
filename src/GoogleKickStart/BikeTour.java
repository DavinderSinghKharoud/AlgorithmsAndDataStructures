package GoogleKickStart;


import java.util.Scanner;

class BikeTour {

    public static void main(String[] args) {
        Scanner input = new Scanner( System.in );
        int tests = input.nextInt();
        for (int ks = 1; ks <= tests; ks++) {
            int num = input.nextInt();
            System.out.println(String.format("Case #%d: %d", ks, solve(num, input)));
        }
    }

    private static int solve(int nums, Scanner input ) {

        if( nums <= 1 ){
            return 0;
        }
        int[] arr = new int[nums];
        for (int i = 0; i < nums; i++) {
            int num = input.nextInt();
            arr[i] = num;
        }


        int res = 0;

        for( int i = 1; i<nums - 1; i++ ){

            if( arr[i] > arr[i + 1] && arr[i] > arr[i - 1] ){
                res++;
            }
        }

        return res;

    }

}
