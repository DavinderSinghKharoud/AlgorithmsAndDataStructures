package LeetCode.WeeklyContest263;

public class NumbersAscending {
   public static void main(String[] args) {
      NumbersAscending o = new NumbersAscending();
   }

   public boolean areNumbersAscending(String s) {
      int len = s.length();
      int prev = -1;

      for (int i = 0; i < len; i++) {
         int curr = 0;
         while (Character.isDigit(s.charAt(i))) {
            curr = curr * 10 + s.charAt(i);
            i++;
         }
         if (curr != 0) {
            if (curr < prev)
               return false;
            prev = curr;
         }
      }
      return true;
   }
}
