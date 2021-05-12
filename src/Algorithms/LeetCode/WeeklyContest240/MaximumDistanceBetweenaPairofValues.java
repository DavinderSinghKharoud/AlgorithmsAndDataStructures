package Algorithms.LeetCode.WeeklyContest240;

public class MaximumDistanceBetweenaPairofValues {
   public static void main(String[] args) {
      MaximumDistanceBetweenaPairofValues obj = new MaximumDistanceBetweenaPairofValues();
      System.out.println(obj.maxDistance1(
            new int[] { 9819, 9508, 7398, 7347, 6337, 5756, 5493, 5446, 5123, 3215, 1597, 774, 368, 313 },
            new int[] { 9933, 9813, 9770, 9697, 9514, 9490, 9441, 9439, 8939, 8754, 8665, 8560 }));
       System.out.println(obj.maxDistance1(
               new int[] { 2,2,2 },
               new int[] {10,10,1}));
   }

   public int maxDistance1(int[] nums1, int[] nums2) {
      int ans = 0;
      int j = -1;
      for (int i = 0; i < nums1.length; i++) {
         while (j + 1< nums2.length && (j + 1< i || nums2[j + 1] >= nums1[i])) {
            j++;
         }
         ans = Math.max(ans, j - i - ((j == nums2.length) ? 1 : 0));
      }
      return ans;

   }

   public int maxDistance(int[] nums1, int[] nums2) {
      int res = 0;
      for (int i = 0; i < nums1.length; i++) {
         int index = find(nums2, nums1[i], i);
         if (index == -1)
            continue;
         if (nums2[index] < nums1[i]) {
            if (index - 1 >= i && nums2[index - 1] >= nums1[i])
               res = Math.max(res, index - 1 - i);
         } else
            res = Math.max(res, index - i);
      }
      return res;
   }

   int find(int[] arr, int val, int s) {
      if (s >= arr.length)
         return -1;
      int l = s, r = arr.length - 1;
      while (l < r) {
         int mid = (r + l + 1) >> 1;
         if (arr[mid] < val) {
            r = mid - 1;
         } else
            l = mid;
      }
      return l;
   }

   int search(int[] arr, int val, int s) {
      if (s >= arr.length)
         return -1;
      int l = s, r = arr.length - 1;
      while (l < r) {
         int mid = (r + l) >> 1;
         if (arr[mid] >= val) {
            r = mid;
         } else
            l = mid + 1;
      }
      return l;
   }
}
