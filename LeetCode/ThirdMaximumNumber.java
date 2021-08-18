package LeetCode;

public class ThirdMaximumNumber {
    public static void main(String[] args) {
        ThirdMaximumNumber o = new ThirdMaximumNumber();
        System.out.println(o.thirdMax(new int[]{ 2,2,3,1 }));
    }

    public int thirdMax(int[] nums) {
        long a = Long.MIN_VALUE, b = Long.MIN_VALUE, c = Long.MIN_VALUE;

        for (int num : nums) {
            a = Math.max(num, a);
        }
        for (int num : nums) {
            if (num != a) b = Math.max(num, b);
        }
        for (int num : nums) {
            if (num != a && num != b) c = Math.max(num, c);
        }
        return (int) ((c == Long.MIN_VALUE) ? a : c);
    }
}
