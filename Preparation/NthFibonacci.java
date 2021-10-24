package Preparation;

/**
 * Given an integer NumberOfValidWords you need to find the Ath fibonacci number modulo 109 + 7.
 *
 * The first fibonacci number F1 = 1
 *
 * The first fibonacci number F2 = 1
 *
 * The nth fibonacci number Fn = Fn-1 + Fn-2 (n > 2)
 *
 *
 *
 * Problem Constraints
 * 1 <= NumberOfValidWords <= 109.
 */
public class NthFibonacci {
    int mod = (int) (1e9 + 7);

    public int solve(int n) {
        int first = 1, second = 1;
        if (n <= 2) return 1;
        n -= 2;
        while (n-- > 0) {
            int third = first + second;
            first = second;
            second = third % mod;
        }
        return second;
    }

    public static void main(String[] args) {
        NthFibonacci nthFibonacci = new NthFibonacci();
        System.out.println(nthFibonacci.solve2(50));
    }

    int[][] base = new int[][]{ {1, 1}, {1, 0}};

    /**
     * [ f(3) ]    =  [1 1] [f(2)]
     * [ f(2) ]       [1 0] [f(1)]
     *
     * Accumulate all of them
     * [f(5)]     = [ 1  1] ^ 4   [f(1)]
     * [f(4)]       [1   0]      [f(0)]
     *
     *
     *
     */
    public int solve2(int n) {
        if( n <= 2) return 1;
        int[][] mat = find(n);
        // System.out.println(mat[0][0] + " " + mat[0][1] + " " + mat[1][0] + " " + mat[1][1]);
        return mat[0][1];
    }

    int[][] find(int n){
        if(n <= 1) return base;
        int[][] half = find(n/2);
        int[][] full = multiplyMatrix(half, half);
        if(n % 2 == 1){
            //Odd
            return multiplyMatrix(full, base);
        }
        return full;
    }

    int[][] multiplyMatrix(int[][] a, int[][] b){
        int[][] newMat = new int[2][2];
        newMat[0][0] = sum(mul(b[0][0], a[0][0]), mul(b[1][0], a[0][1]));
        newMat[0][1] = sum(mul(b[0][1], a[0][0]), mul(b[1][1], a[0][1]));
        newMat[1][0] = sum(mul(b[0][0], a[1][0]), mul(b[1][0], a[1][1]));
        newMat[1][1] = sum(mul(b[0][1], a[1][0]), mul(b[1][1], a[1][1]));
        return newMat;
    }

    int mul(int a, int b){
        long m = (long)a * b;
        return (int)(m % mod);
    }

    int sum(int a, int b){
        int sum = (a + b) % mod;
        while(sum < 0) sum += mod;
        return sum;
    }

}
