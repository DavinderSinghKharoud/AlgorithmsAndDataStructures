package ProjectEuler;

/**
 * FindGreatestCommonDivisor Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 *
 * a2 + b2 = c2
 * For example, 32 + 42 = 9 + 16 = 25 = 52.
 *
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
public class PythogoreanTriplet {

    public static void main(String[] args) {
        getTriplet( 1000);
        
    }

    private static void getTriplet(int total) {

        for( int i = 1; i<=total; i++){

            int a = i*i;

            for( int j = i; j<total-i; j++){
                 int b = j*j;
                 int c = a + b;

                 if( checkPerfectSquare( c ) ){

                     if( (i+j+Math.sqrt(c)) == 1000){
                         System.out.println("a:"+i + " b:"+j +" c:"+ Math.sqrt(c));
                         System.out.println( i * j * Math.floor( Math.sqrt(c) ) );
                         return;
                     }
                 }
            }
        }
    }

    private static boolean checkPerfectSquare(int num) {

        double sq = Math.sqrt( num );

        return ( ( sq - Math.floor(sq)) == 0);

    }
}
