package LeetCode.BiweeklyContest58;

import java.util.stream.IntStream;

public class MaximumProductPalindrome {
   public static void main(String[] args) {
      MaximumProductPalindrome o = new MaximumProductPalindrome();
      System.out.println(o.maxProduct2("zaaaxbbby"));
   }

   public long maxProduct(String s) {
      long ans = Long.MIN_VALUE;
      int[] prefix = getMax(s);
      StringBuilder sbr = new StringBuilder(s);
      int[] suffix = getMax(sbr.reverse().toString());
      // reverse the suffix
      suffix = reverse(suffix);

      for (int i = 1; i < s.length(); i++) {
         ans = Math.max(ans, (long) prefix[i - 1] * suffix[i]);
         if (i != s.length() - 1) {
            ans = Math.max(ans, (long) prefix[i] * suffix[i + 1]);
         }
      }
      return ans;
   }

   int[] reverse(int[] arr) {
      return IntStream.rangeClosed(1, arr.length).map(i -> arr[arr.length - i]).toArray();
   }

   int[] getMax(String s) {
      int[] palindromes = manacherOdd(s);
      int[] max = new int[s.length()];

      for (int i = 0; i < s.length(); i++) {
         int curr = palindromes[i];
         int index = i + curr - 1;
         int len = (curr - 1) * 2 + 1;
         max[index] = Math.max(max[index], len);
      }

      // As we only have a answer for longest palindrome at some point, but we don't
      // save the smaller palindromes that are inside that bigger one
      // Example "xbayabx" -- > [1, 1, 1, 4, 1, 1, 1 ]
      // Let's look for index 4, we only save 1 because we choose index 4 to be as
      // center, to find the answer
      // we are considering all the palindromes that ends at index 4, which means we
      // are missing palindrome of length 3 at index 4, as you can see from the
      // example
      // To evalaute that, we need to traverse array from end and find max(arr[i],
      // arr[i + 1] - 2)

      for (int i = s.length() - 2; i >= 0; i--) {
         max[i] = Math.max(max[i], max[i + 1] - 2);
      }

      // Get the prefix
      for (int i = 1; i < s.length(); i++) {
         max[i] = Math.max(max[i], max[i - 1]);
      }
      return max;
   }

   // abbabb -->[1, 1, 1, 3, 1, 1]
   int[] manacherOdd(String s) {
      int[] res = new int[s.length()];
      for (int i = 0, l = 0, r = -1; i < s.length(); i++) {
         int k = (i > r) ? 1 : Math.min(res[l + r - i], r - i + 1);
         while (0 <= i - k && i + k < s.length() && s.charAt(i - k) == s.charAt(i + k)) {
            k++;
         }

         res[i] = k--;
         if (i + k > r) {
            l = i - k;
            r = i + k;
         }
      }
      return res;
   }

   // *******************************************************
   // Rolling Hash
   final long base = 29L;
   final long mod = Integer.MAX_VALUE;

   public long maxProduct2(String s) {
      int n = s.length();
      long[] pow = new long[n + 1];
      pow[0] = 1;
      for (int i = 1; i <= n; i++) {
         pow[i] = (pow[i - 1] * base) % mod;
      }

      long[] lh = new long[n + 1], rh = new long[n + 1];
      for (int i = 1; i <= n; i++)
         lh[i] = (lh[i - 1] * base + (s.charAt(i - 1) - 'a')) % mod;
      for (int i = n - 1; i >= 0; i--)
         rh[i] = (rh[i + 1] * base + (s.charAt(i) - 'a')) % mod;

      int[] left = new int[n], right = new int[n];
      for (int i = 0, max = 1; i < n; i++) {
         if (i > max && isPalindrome(i - max - 1, i + 1, lh, rh, pow))
            max += 2;
         left[i] = max;
      }
      for (int i = n - 1, max = 1; i >= 0; i--) {
         if (i + max + 2 <= n && isPalindrome(i, i + max + 2, lh, rh, pow))
            max += 2;
         right[i] = max;
      }
      long ans = 1;
      for (int i = 1; i < n; i++) {
         ans = Math.max(ans, ((long) left[i - 1] * right[i]));
      }
      return ans;

   }

   boolean isPalindrome(int l, int r, long[] lh, long[] rh, long[] pow) {
      return lh(l, r, lh, pow) == rh(l, r, rh, pow);
   }

   long lh(int l, int r, long[] hash, long[] pow) {
      long ans = (hash[r] - hash[l] * pow[r - l]) % mod;
      return (ans < 0) ? ans + mod : ans;
   }

   long rh(int l, int r, long[] hash, long[] pow) {
      long ans = (hash[l] - hash[r] * pow[r - l]) % mod;
      return (ans < 0) ? ans + mod : ans;
   }
}
