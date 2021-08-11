package LeetCode;

public class CountGoodNumbers {
    static int mod = (int) 1e9 + 7;

    public static void main(String[] args) {
        CountGoodNumbers o = new CountGoodNumbers();
        System.out.println(o.countGoodNumbers(50));
    }

    public int countGoodNumbers(long n) {
        long evens = (n + 1) / 2, odds = n - evens;
        long res = mul(findPower(5, evens), findPower(4, odds));
        return (int) res;
    }

    long findPower(long a, long n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = mul(res, a);
            }
            n >>= 1;
            a = mul(a, a);
        }
        return res % mod;
    }

    long mul(long a, long b) {
        return (a * b) % mod;
    }
}
