package LeetCode;

/**
 * Find the smallest prime palindrome greater than or equal to N.
 *
 * Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1.
 *
 * For example, 2,3,5,7,11 and 13 are primes.
 *
 * Recall that a number is a palindrome if it reads the same from left to right as it does from right to left.
 *
 * For example, 12321 is a palindrome.
 *
 *
 *
 * Example 1:
 *
 * Input: 6
 * Output: 7
 * Example 2:
 *
 * Input: 8
 * Output: 11
 * Example 3:
 *
 * Input: 13
 * Output: 101
 */
public class PrimePalindrome {

   public static void main(String[] args) {

      System.out.println(primePalindrome(999));
   }

   public static int primePalindrome(int n) {
      if(n == 1) return 2;
      String s = String.valueOf(n);
      if (reverse(s).equals(s) && isPrime(n))
         return n;

      while (true) {
         String next = findNext(s);
         if (isPrime(Integer.parseInt(next)))
            return Integer.parseInt(next);
         s = next;
      }
   }

   public static String findNext(String s) {
      String rev = reverse(s);
      StringBuilder sbr = new StringBuilder(s);

      int index = (s.length() - 1) / 2;
      boolean isCarry = true;
      while (index >= 0) {
         if (sbr.charAt(index) != '9') {
            isCarry = false;
            sbr.replace(index, index + 1, String.valueOf(((sbr.charAt(index) - '0') + 1)));
            break;
         }else{
            sbr.replace(index, index + 1, "0");
            index--;
         }
      }

      if (index < 0 && isCarry) {
         sbr.insert(0, "1");
      }

      String rev2 = reverse((sbr.toString()));
      if( rev.compareTo(s) > 0 && rev.compareTo(rev2) < 0){
         return rev;
      }
      return rev2;
   }

   public static boolean isPrime(int n) {
      int sqrt = (int) Math.sqrt(n);
      for (int i = 2; i <= sqrt; i++) {
         if (n % i == 0)
            return false;
      }
      return true;
   }

   public static String reverse(String s) {
      StringBuilder sbr = new StringBuilder(s.substring(0, s.length() / 2));
      String rev = sbr.reverse().toString();
      sbr.reverse();
      return ((s.length() & 1) == 0) ? sbr.append(rev).toString()
            : sbr.append(s.charAt(s.length() / 2)).append(rev).toString();
   }
}
