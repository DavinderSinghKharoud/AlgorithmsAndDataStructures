package algorithms.LeetCode;

public class GreatestCommonDivisor {
    public static int generalizedGCD(int num, int[] arr){

        if( num == 0){
            return 0;
        }

        int temp_gcd = arr[0];
        for( int i = 1; i<num; i++ ){
            temp_gcd = gcd( temp_gcd, arr[i]);

        }

        return temp_gcd;
    }

    public static int gcd( int x, int y ){
        while ( x != y ){
            if( x > y ){
                x -= y;
            }else {
                y -= x;
            }
        }
        return x;
    }
    public static void main(String[] args) {

        System.out.println( generalizedGCD(5, new int[]{
                2,4,6,8,10
        }));
    }
}
