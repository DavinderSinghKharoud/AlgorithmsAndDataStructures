
//Time complexity  O(k log^3n)
class MillerRabin {

   public boolean isPrime(long num) {
      if (num < 2)
         return false;

      int r = 0;
      long d = num - 1;
      while ((d & 1) == 0) {
         // Count the powers of two
         r++;
         d >>= 1;
      }

      for (int a : new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37 }) {
         if (num == a)
            return true;
         if (checkComposite(num, a, d, r)) {
            return false;
         }
      }
      return true;
   }

   private boolean checkComposite(long num, long a, long d, int s) {
      long curr = binPower(a, d, num);
      if (curr == 1 || curr == num - 1)
         return false;

      for (int r = 1; r < s; r++) {
         curr = curr * curr % num;
         if (curr == num - 1)
            return false;
      }
      return true;
   }

   public long binPower(long base, long power, long mod) {
      long res = 1;
      base %= mod;
      while (power > 0) {
         if ((power & 1) == 1) {
            res = res * base % mod;
         }
         base = base * base % mod;
         power >>= 1;
      }
      return res;
   }

}
