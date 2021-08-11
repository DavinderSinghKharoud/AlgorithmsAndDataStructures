package LeetCode;

public class MinSwapsNeeded {

   public static void main(String[] args) {

   }

   public int minSwaps(String s) {
      int unbalanced = 0;
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (c == '[') {
            unbalanced++;
         } else {
            if (unbalanced > 0) {
               unbalanced--;
            }
         }
      }
      return (unbalanced + 1) / 2;
   }
}
