package ProjectEuler;

/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10
 * without any remainder.
 *
 * What is the smallest positive number that is evenly divisible by all of the numbers
 * from 1 to 20?
 */
public class SmallestMultiple {

    public static void main(String[] args) {

        System.out.println(getSmallestMultiple(20));

        //System.out.println(checkPrime(13));
    }

    private static int getSmallestMultiple(int limit) {

        int num = 1;

        while (true) {
            if (check(num, limit)) {
                break;
            }
            num++;
        }
        return num;

    }

    private static boolean check(int num, int limit) {

        for (int i = 1; i <= limit; i++) {
            if (num % i != 0) {
                return false;
            }
        }

        return true;
    }


}
