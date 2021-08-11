package LeetCode.WeeklyContest243;

public class WordEquals {
   public static void main(String[] args) {

   }

   public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
      int first, second, third;
      StringBuilder sbr = new StringBuilder();
      for (int i = 0; i < firstWord.length(); i++) {
         sbr.append(firstWord.charAt(i) - 'a');
      }
      first = Integer.parseInt(sbr.toString());
      sbr = new StringBuilder();
      for (int i = 0; i < secondWord.length(); i++) {
         sbr.append(secondWord.charAt(i) - 'a');
      }
      second = Integer.parseInt(sbr.toString());
      sbr = new StringBuilder();

      for (int i = 0; i < targetWord.length(); i++) {
         sbr.append(targetWord.charAt(i) - 'a');
      }

      third = Integer.parseInt(sbr.toString());
      return first + second == third;
   }
}
