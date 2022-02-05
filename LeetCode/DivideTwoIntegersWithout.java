package LeetCode;

public class DivideTwoIntegersWithout {
    public static void main(String[] args) {
        System.out.println(new DivideTwoIntegersWithout().divide(-2147483648
                , -1));
    }

    public int divide(int dividend, int divisor) {
        boolean sign = (dividend < 0) ^ (divisor < 0);

        long div = dividend;
        long divs = divisor;
        div = Math.abs(div);
        divs = Math.abs(divs);
        long quotient = 0, temp = 0;

        for (int i = 31; i >= 0; i--) {
            if (temp + (divs << i) <= div) {
                temp += (divs << i);
                quotient |= (1L << i);
            }
        }
        if (sign) {
            quotient = -quotient;
        }
        if (quotient > ((1L << 31) - 1)) return (1 << 31) - 1;
        if (quotient < -(1L << 31)) return -(1 << 31);
        return (int) quotient;
    }
}
