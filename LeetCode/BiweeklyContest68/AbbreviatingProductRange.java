package LeetCode.BiweeklyContest68;

/**
 * ou are given two positive integers left and right with left <= right. Calculate the product of all integers in the inclusive range [left, right].
 *
 * Since the product may be very large, you will abbreviate it following these steps:
 *
 * Count all trailing zeros in the product and remove them. Let us denote this count as DestroyingAsteroids.
 * For example, there are 3 trailing zeros in 1000, and there are 0 trailing zeros in 546.
 * Denote the remaining number of digits in the product as d. If d > 10, then express the product as <pre>...<suf> where <pre> denotes the first 5 digits of the product, and <suf> denotes the last 5 digits of the product after removing all trailing zeros. If d <= 10, we keep it unchanged.
 * For example, we express 1234567654321 as 12345...54321, but 1234567 is represented as 1234567.
 * Finally, represent the product as a string "<pre>...<suf>eC".
 * For example, 12345678987600000 will be represented as "12345...89876e5".
 * Return a string denoting the abbreviated product of all integers in the inclusive range [left, right].
 *
 *
 *
 * Example 1:
 *
 * Input: left = 1, right = 4
 * Output: "24e0"
 * Explanation:
 * The product is 1 × 2 × 3 × 4 = 24.
 * There are no trailing zeros, so 24 remains the same. The abbreviation will end with "e0".
 * Since the number of digits is 2, which is less than 10, we do not have to abbreviate it further.
 * Thus, the final representation is "24e0".
 * Example 2:
 *
 * Input: left = 2, right = 11
 * Output: "399168e2"
 * Explanation:
 * The product is 39916800.
 * There are 2 trailing zeros, which we remove to get 399168. The abbreviation will end with "e2".
 * The number of digits after removing the trailing zeros is 6, so we do not abbreviate it further.
 * Hence, the abbreviated product is "399168e2".
 * Example 3:
 *
 *
 * Input: left = 999998, right = 1000000
 * Output: "99999...00002e6"
 * Explanation:
 * The above diagram shows how we abbreviate the product to "99999...00002e6".
 * - It has 6 trailing zeros. The abbreviation will end with "e6".
 * - The first 5 digits are 99999.
 * - The last 5 digits after removing trailing zeros is 00002.
 */
public class AbbreviatingProductRange {
   public static void main(String[] args) {
      AbbreviatingProductRange o = new AbbreviatingProductRange();
      System.out.println(o.abbreviateProduct(1, 4));
   }

   public String abbreviateProduct(int left, int right) {
      long suffix = 1, maxSuffix = (long) 1e10;
      double prefix = 1.0, maxPrefix = 100000;
      boolean overMax = false;
      int zeros = 0;

      for (int i = left; i <= right; i++) {
         prefix *= i;
         suffix *= i;

         while (prefix >= maxPrefix) {
            prefix /= 10;
         }

         while (suffix % 10 == 0) {
            // remove the trailing zeros
            zeros++;
            suffix /= 10;
         }

         overMax |= (suffix > maxSuffix);
         // We only save the last non zero 12 digits
         suffix %= maxSuffix;
      }

      if (!overMax) {
         return suffix + "e" + zeros;
      }
      String s = String.valueOf(suffix);
      return "" + (int) (prefix) + "..." + s.substring(s.length() - 5) + "e" + zeros;
   }

}
