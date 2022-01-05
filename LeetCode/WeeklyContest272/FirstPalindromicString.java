package LeetCode.WeeklyContest272;

public class FirstPalindromicString {
   public static void main(String[] args) {
      FirstPalindromicString o = new FirstPalindromicString();
   }

   public String firstPalindrome(String[] words) {
      for (String word : words) {
         if (isPalindrome(word))
            return word;
      }
      return "";
   }

   boolean isPalindrome(String word) {
      int start = 0, end = word.length() - 1;
      while (start <= end) {
         if (word.charAt(start++) != word.charAt(end--))
            return false;
      }
      return true;
   }
}
