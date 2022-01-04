package LeetCode.BiweeklyContest68;

public class MaxWordsFoundInSentences {
   public static void main(String[] args) {
      MaxWordsFoundInSentences o = new MaxWordsFoundInSentences();
   }

   public int mostWordsFound(String[] sentences) {
      int count = 0;
      for (String sen : sentences) {
         int curr = 0;
         sen = sen.trim();
         if (sen.length() == 0)
            continue;
         for (int i = 0; i < sen.length(); i++) {
            if (sen.charAt(i) == ' ')
               curr++;
         }
         curr++;
         count = Math.max(count, curr);
      }
      return count;
   }
}
