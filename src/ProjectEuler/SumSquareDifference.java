package ProjectEuler;

/**
 * The sum of the squares of the first ten natural numbers is,
 *
 * 12+22+...+102=385
 * The square of the sum of the first ten natural numbers is,
 *
 * (1+2+...+10)2=552=3025
 * Hence the difference between the sum of the squares of the
 * first ten natural numbers and the square of the sum is 3025−385=2640.
 *
 * Find the difference between the sum of the squares of the
 * first one hundred natural numbers and the square of the sum.
 */
public class SumSquareDifference {

    public static void main(String[] args) {

        System.out.println( getDifference( 100 ));
    }

    private static int getDifference(int limit) {

        int squareSum = 0;

        for( int i = 1; i<=limit; i++){

            squareSum += Math.pow(i, 2);
        }

        int sumSquare = 0;

        for( int i = 1; i<= limit; i++){
            sumSquare += i;
        }

        sumSquare = (int) Math.pow( sumSquare, 2);

        return sumSquare - squareSum;

    }
}
