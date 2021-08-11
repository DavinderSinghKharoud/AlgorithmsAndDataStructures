package LeetCode.BiweeklyContest50;

public class MinimumOperationsToMakeStringSorted {
   public static void main(String[] args) {

      MinimumOperationsToMakeStringSorted obj = new MinimumOperationsToMakeStringSorted();
      System.out.println(obj.makeStringSorted("cdbea"));
   }

   int mod = (int) (1e9) + 7;

   public int makeStringSorted(String s) {
      int len = s.length();
      long ans = 0;
      long[] fac = new long[len + 1];
      fac[0] = 1;
      int[] count = new int[26];
      for (char c : s.toCharArray()) {
         count[c - 'a']++;
      }

      for (int i = 1; i <= len; i++) {
         fac[i] = fac[i - 1] * i % mod;
      }

      for (int i = 0; i < len; i++) {
         long total = fac[len - 1 - i];
         for (int j = 0; j < s.charAt(i) - 'a'; j++) {
            // Check all the smaller characters that can be placed
            if (count[j] > 0) {
               count[j]--;

               long rev = 1;
               for (int k = 0; k < 26; k++) {
                  rev = (rev * fac[count[k]]) % mod;
               }

               ans = (ans + (total * inverse(rev)) % mod) % mod;
               count[j]++;
            }
         }
         count[s.charAt(i) - 'a']--;
      }
      return (int) ans % mod;
   }

   long inverse(long num) {
      return expo(num, mod - 2);
   }

   long expo(long num, long power) {
      long ans = 1;

      while (power > 0) {
         if ((power & 1) == 1) {
            ans = (ans * num) % mod;
         }
         num = (num * num) % mod;
         power >>= 1;
      }

      return ans;
   }
}
