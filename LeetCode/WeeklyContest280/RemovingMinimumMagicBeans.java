package LeetCode.WeeklyContest280;

import java.util.Arrays;

public class RemovingMinimumMagicBeans {
    public static void main(String[] args) {
        RemovingMinimumMagicBeans o = new RemovingMinimumMagicBeans();
        System.out.println(o.minimumRemoval(new int[]{
                4, 1, 6, 5
        }));
    }

    public long minimumRemoval(int[] beans) {
        int len = beans.length;
        long sum = 0;
        long exclude = 0;
        for (int num : beans) sum += num;

        Arrays.sort(beans);

        long ans = sum;
        for (int i = 0; i < len; i++) {
            int bean = beans[i];

            long equating = (sum - exclude - bean) - ((long) bean * (len - i - 1));
            ans = Math.min(ans, equating + exclude);
            exclude += bean;
        }
        return ans;
    }
}

