/*
Implement pow(x, n) % d.

In other words, given x, n and d,

find (xn % d)

Note that remainders on division cannot be negative. 
In other words, make sure the answer you return is non negative.

Input : x = 2, n = 3, d = 3
Output : 2

2^3 % 3 = 8 % 3 = 2.
 */

public class PowerFunction {

    public static int pow(int x, int n, int d) {
        if (x == 0) return 0;
        if (n == 0) return 1;

       return (int) findPow(x * 1L, n * 1L, d * 1l);


    }

    private static long findPow(long x, long n, long d) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) return 1;
        if (n % 2 == 0) {
            return ((findPow((x * x) % d, n / 2, d) % d) + d) % d;
        } else {
            return ((x * findPow((x * x) % d, n / 2, d) % d) + d) % d;
        }
    }

    public static void main(String[] args) {

        System.out.println(pow(2, 4, 3));
    }
}

