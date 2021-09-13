package LeetCode.WeeklyContest258;

public class ReversePrefix {
   public static void main(String[] args) {
      ReversePrefix o = new ReversePrefix();
   }

   public String reversePrefix(String word, char ch) {
      StringBuilder sbr = new StringBuilder();
      for (int i = 0; i < word.length(); i++) {
         char c = word.charAt(i);
         sbr.append(c);
         if (c == ch) {
            return sbr.reverse().toString() + word.substring(i + 1, word.length());
         }
      }
      return word;
   }
}
