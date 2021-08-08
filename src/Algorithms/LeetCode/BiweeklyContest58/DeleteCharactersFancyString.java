package Algorithms.LeetCode.BiweeklyContest58;

public class DeleteCharactersFancyString {
   public static void main(String[] args) {
      DeleteCharactersFancyString o = new DeleteCharactersFancyString();
   }

   public String makeFancyString(String s) {
      StringBuilder sbr = new StringBuilder();
      StringBuilder temp = new StringBuilder();
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (temp.length() == 0)
            temp.append(c);
         else {
            if (temp.charAt(temp.length() - 1) == c) {
               if (temp.length() < 2)
                  temp.append(c);
            } else {
               sbr.append(temp.toString());
               temp = new StringBuilder();
            }
         }
      }
      return sbr.toString();
   }
}
