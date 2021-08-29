package LeetCode.WeeklyContest252;

public class MinGardenPerimeter {
   public static void main(String[] args) {
      MinGardenPerimeter o = new MinGardenPerimeter();
      System.out.println(o.minimumPerimeter(2784381467700L));
   }

   public long minimumPerimeter(long neededApples) {
      long ans = 0;
      long start = 1, end = (long) 1e5;
      while (start <= end) {
         long mid = ((end - start) >> 1) + start;
         if (poss(mid, neededApples)) {
            end = mid - 1;
            ans = mid * 8;
         } else {
            start = mid + 1;
         }
      }
      return ans;
   }

   boolean poss(long per, long neededApples) {
      long sum = (per * (per + 1)) / 2;
      sum *= 2;
      long count = 0;
      for (int i = 1; i <= per; i++) {
         count += (i * per * 2) + i;
         count += sum;
      }
      count *= 2;
      count += sum;
      return count >= neededApples;
   }
}
