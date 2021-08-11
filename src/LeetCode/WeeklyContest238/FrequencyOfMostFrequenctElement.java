package LeetCode.WeeklyContest238;

import java.util.*;

public class FrequencyOfMostFrequenctElement {
   public static void main(String[] args) {
      FrequencyOfMostFrequenctElement obj = new FrequencyOfMostFrequenctElement();
      System.out.println(obj.maxFrequency3(new int[] {1,2,4}, 5));
   }

   public int maxFrequency3(int[] nums, int k) {
      int len = nums.length;
      shuffle(nums);
      Arrays.sort(nums);
      int ans = 1;
      long sum = 0;
      int start = 0;
      // We will use sliding window approach
      for (int end = 0; end < len; end++) {
         sum += nums[end];
         // We will decrease the window until it is valid
         while (start < end && (long) nums[end] * (end - start + 1) - sum >k) {
            sum -= nums[start];
            start++;
         }

         ans = Math.max(ans, end - start + 1);
      }
      return ans;
   }

   public int maxFrequency2(int[] nums, int k) {
      shuffle(nums);
      Arrays.sort(nums);
      int ans = 1;
      long[] prefix = new long[nums.length];
      prefix[0] = nums[0];

      for (int i = 1; i < nums.length; i++) {
         prefix[i] = prefix[i - 1] + nums[i];
      }

      for (int i = 0; i < prefix.length; i++) {
         // For each index find the max frequency
         ans = Math.max(ans, search(prefix, nums, i, k));
      }
      return ans;
   }

   long getSum(long[] prefix, int a, int b) {
      return prefix[b] - ((a - 1 >= 0) ? prefix[a - 1] : 0);
   }

   int search(long[] prefix, int[] nums, int index, int k) {
      int ans = 1;
      int start = 0, end = index;
      while (start <= end) {
         int mid = (end + start) / 2;

         if (getSum(prefix, mid, index) + k >= (long) (index - mid + 1) * nums[index]) {
            ans = Math.max(ans, index - mid + 1);
            end = mid - 1;
         } else {
            start = mid + 1;
         }
      }
      return ans;
   }

   public int maxFrequency(int[] nums, int k) {
      int ans = 1;
      shuffle(nums);
      Arrays.sort(nums);
      for (int i = 0; i < nums.length; i++) {
         int curr = 0;
         int remain = k;
         int suffix = 0;
         for (int j = i - 1; j >= 0; j--) {
            int dis = nums[j + 1] - nums[j];
            dis += suffix;
            if (dis <= remain) {
               curr++;
               ans = Math.max(ans, curr + 1);
               remain -= dis;
            } else
               break;
            suffix += nums[j + 1] - nums[j];
         }
      }
      return ans;
   }

   static void shuffle(int[] aa) {
      int n = aa.length;
      Random rand = new Random();
      for (int i = 1; i < n; i++) {
         int j = rand.nextInt(i + 1);
         int tmp = aa[i];
         aa[i] = aa[j];
         aa[j] = tmp;
      }
   }
}
