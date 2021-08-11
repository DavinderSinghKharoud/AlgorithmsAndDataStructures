package LeetCode;

public class NthMagicalNumber {

    public static void main(String[] args) {
        NthMagicalNumber o = new NthMagicalNumber();
        System.out.println(o.nthMagicalNumber(12312, 12312, 3123));
    }

    int mod = (int) 1e9 + 7;

    public int nthMagicalNumber(int n, int a, int b) {
        if (a > b) {
            a = a ^ b ^ (b = a);
        }
        int gcd = gcd(a, b);
        long lcm = (int) (((long) a * b) / gcd);
        // For each state, the rank of number (x) = x/a + x/b - x/lcm
        long low = a, high = (long) a * n;
        while (low < high) {
            long mid = (high - low) >> 1;
            mid += low;
            long cal = mid / a + mid / b - mid / lcm;
            if (cal < n) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return (int) (high % mod);
    }

    int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
}
