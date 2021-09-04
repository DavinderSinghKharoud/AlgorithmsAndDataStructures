package LeetCode.WeeklyContest256;

import java.util.*;

public class FindKthLargest {
    public static void main(String[] args) {
        FindKthLargest o = new FindKthLargest();
//        System.out.println(o.kthLargestNumber(new String[]{"623986800", "3", "887298", "695", "794", "6888794705", "269409", "59930972", "723091307", "726368", "8028385786", "378585"}, 11));
        System.out.println(o.kthLargestNumber(new String[]{"2","21", "23","21","1122","1", "2312312123123"}, 1));
    }

    public String kthLargestNumber(String[] nums, int k) {


        Arrays.sort(nums, (o1, o2) -> {
            if (o1.length() != o2.length()) {
                return Integer.compare(o1.length(), o2.length());
            }
            for (int i = 0; i < o1.length(); i++) {
                if (o1.charAt(i) != o2.charAt(i)) {
                    return Integer.compare(o1.charAt(i), o2.charAt(i));
                }
            }
            return 0;
        });

        return String.valueOf(nums[nums.length - k]);
    }

    static void shuffleSort(int[] aa) {
        int n = aa.length;
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int tmp = aa[i];
            aa[i] = aa[j];
            aa[j] = tmp;
        }
        Arrays.sort(aa);
    }
}
