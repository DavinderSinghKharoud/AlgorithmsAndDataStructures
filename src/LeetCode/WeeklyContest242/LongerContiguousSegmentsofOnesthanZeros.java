package LeetCode.WeeklyContest242;

public class LongerContiguousSegmentsofOnesthanZeros {
   public static void main(String[] args) {

   }

   public boolean checkZeroOnes(String s) {
      int zeroes = 0;
      int curr = 0;
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (c == '0') {
            curr++;
         } else
            curr = 0;
         zeroes = Math.max(zeroes, curr);
      }
      curr = 0;
      int ones = 0;
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (c == '1') {
            curr++;
         } else
            curr = 0;
         ones = Math.max(ones, curr);
      }
      return ones > zeroes;
   }
}
