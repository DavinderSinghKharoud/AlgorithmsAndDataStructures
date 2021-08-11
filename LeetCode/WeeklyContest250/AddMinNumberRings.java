package LeetCode.WeeklyContest250;

public class AddMinNumberRings {
   public static void main(String[] args) {

   }

   public int addRungs(int[] rungs, int dist) {
      int res = 0;
      int len = rungs.length;
      if (rungs[0] > dist)
         res++;
      for (int i = 0; i < len - 1; i++) {
         res += howMany(rungs[i], rungs[i + 1], dist);
      }
      return res;
   }

   int howMany(int a, int b, int dist) {
      if (b - a <= dist)
         return 0;
      return (b - a) / dist;
   }
}
