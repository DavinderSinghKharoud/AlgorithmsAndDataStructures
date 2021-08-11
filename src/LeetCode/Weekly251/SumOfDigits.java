package LeetCode.Weekly251;

public class SumOfDigits {
   public static void main(String[] args) {
      SumOfDigits o = new SumOfDigits();
   }

   public int getLucky(String s, int k) {
      StringBuilder sbr = new StringBuilder();
      for (char c : s.toCharArray()) {
         sbr.append((c - 'a') + 1);
      }

      while (k-- > 0) {
         long curr = convert(sbr.toString());
         sbr = new StringBuilder(String.valueOf(curr));
      }
      return Integer.valueOf(sbr.toString());
   }

   long convert(String num) {
      long sum = 0;
      for (int i = 0; i < num.length(); i++) {
         sum += (num.charAt(i) - '0');
      }
      return sum;
   }
}
