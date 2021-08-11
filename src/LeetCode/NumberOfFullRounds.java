package LeetCode;

public class NumberOfFullRounds {

   public static void main(String[] args) {
      NumberOfFullRounds o = new NumberOfFullRounds();
      System.out.println(o.numberOfRounds("20:00", "06:00"));
   }

   public int numberOfRounds(String startTime, String finishTime) {
      int res = 0;
      int hour = Integer.parseInt(startTime.substring(0, 2)), minute = Integer.parseInt(startTime.substring(3, 5));
      int end_hour = Integer.parseInt(finishTime.substring(0, 2)),
            end_min = Integer.parseInt(finishTime.substring(3, 5));

      int count = 0;
      while (hour != end_hour || minute != end_min) {
         if (minute % 15 == 0) {
            if (count == 15) {
               res++;
            }
            count = 0;
         }
         count++;
         minute++;
         if (minute == 60) {
            hour++;
            if (hour == 24) {
               hour = 0;
            }
            minute = 0;
         }
      }
      if (count == 15 && (minute % 15 == 0))
         res++;
      return res;
   }
}
