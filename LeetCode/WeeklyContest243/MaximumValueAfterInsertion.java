package LeetCode.WeeklyContest243;

public class MaximumValueAfterInsertion {
   public static void main(String[] args) {
    MaximumValueAfterInsertion o =new MaximumValueAfterInsertion();
       System.out.println(o.maxValue("45231233", 3));
   }

   public String maxValue(String n, int x) {
      boolean isNeg = n.charAt(0) == '-';
      if (isNeg) {
         for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);
            if ((c - '0') > x) {
               return n.substring(0, i) + x + n.substring(i);
            }
         }
         return n + x;
      } else {
         for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);
            if ((c - '0') < x) {
               return n.substring(0, i) + x + n.substring(i);
            }
         }
         return n + x;
      }
   }
}
