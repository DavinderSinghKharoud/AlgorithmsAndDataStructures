package Algorithms.LeetCode;

public class isPowerOfFour {
    public static boolean isPowerOfFour(int num) {
        if( num == 1 ) return true;
        int temp = 1;
        for( int i = 1; i<Math.sqrt(num); i++ ){
            temp *= 4;
            if( temp == num ){return true;}
        }

        return false;
    }


    public static void main(String[] args) {
        System.out.println( isPowerOfFour2(8));
    }

    public static boolean isPowerOfFour2(int num) {
        System.out.println((num -1 ) % 3 );
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
    }
}
