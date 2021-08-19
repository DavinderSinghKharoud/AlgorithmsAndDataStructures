package LeetCode;

public class MinimumNonZeroProduct {
    public static void main(String[] args) {
        MinimumNonZeroProduct minimumNonZeroProduct = new MinimumNonZeroProduct();
        System.out.println(minimumNonZeroProduct.minNonZeroProduct(55));
    }

    int mod = (int) 1e9 + 7;

    public int minNonZeroProduct(int p) {
        long last = (1L << p) - 1;
        return (int) (mul(last, pow(last - 1, last / 2)));
    }

    long pow(long num, long pow) {
        long ans = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                ans = mul(ans, num);
            }
            pow >>= 1;
            num = mul(num, num);
        }
        return ans;
    }

    long mul(long a, long b) {
        if(a >= mod) a %= mod;
        if( b >= mod) b %= mod;
        return (a * b) % mod;
    }
}
