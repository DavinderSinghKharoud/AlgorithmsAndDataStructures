package LeetCode.BiweeklyContest71;

public class MinSumFourDigits {
    public static void main(String[] args) {
        MinSumFourDigits o = new MinSumFourDigits();
        System.out.println(o.minimumSum(2932));
    }

    int min = Integer.MAX_VALUE;

    public int minimumSum(int num) {
        int[] arr = new int[4];
        int i = 0;
        while (num > 0) {
            arr[i++] = num % 10;
            num /= 10;
        }
        int mask = 0;
        for (int j = 0; j < arr.length; j++) {
            mask = (mask | (1 << j));
        }

        find(new StringBuilder(), new StringBuilder(), arr, mask);
        return min;
    }

    private void find(StringBuilder first, StringBuilder second, int[] arr, int mask) {
        if (mask == 0) {
            if(first.isEmpty() || second.isEmpty()) return;
            min = Math.min(Integer.parseInt(first.toString()) + Integer.parseInt(second.toString()), min);
        } else {
            for (int i = 0; i < arr.length; i++) {
                if ((mask & (1 << i)) > 0) {
                    find(first.append(arr[i]), second, arr, (mask ^ (1 << i)));
                    first.deleteCharAt(first.length() - 1);
                    find(first, second.append(arr[i]), arr, (mask ^ (1 << i)));
                    second.deleteCharAt(second.length() - 1);
                }
            }
        }
    }
}
