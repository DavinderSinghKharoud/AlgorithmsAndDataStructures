package LeetCode.BiweeklyContest59;

public class SpecialTypeWriter {
   public static void main(String[] args) {
      SpecialTypeWriter o = new SpecialTypeWriter();

       System.out.println(o.minTimeToType("bza"));
   }

   public int minTimeToType(String word) {
      int len = word.length();
      char start = 'a';
      int time = 0;
      for (int i = 0; i < len; i++) {
         char c = word.charAt(i);
         time += minMoves(c, start) + 1;
         start = c;
      }
      return time;
   }

   int minMoves(char target, char curr) {
      if (curr == target)
         return 0;
      if (curr < target) {
         int min = target - curr;
         return Math.min(min, curr - 'a' + ('z' - target + 1));
      }
      int min = curr - target;
      return Math.min(min, 'z' - curr + 1 + (target - 'a'));
   }

}
