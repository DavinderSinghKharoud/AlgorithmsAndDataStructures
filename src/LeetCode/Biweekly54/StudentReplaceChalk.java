package LeetCode.Biweekly54;

public class StudentReplaceChalk {
   public static void main(String[] args) {
        StudentReplaceChalk o = new StudentReplaceChalk();
       System.out.println(o.chalkReplacer(new int[]{3,4,1,2}, 25));
   }


   public int chalkReplacer(int[] chalk, int k) {
      long reducedInOne = 0;
      int len = chalk.length;
      for (int i = 0; i < len; i++) {
         if (chalk[i] > k)
            return i;
         else {
            reducedInOne += chalk[i];
            k -= chalk[i];
         }
      }

      // Reduce it until last

      while (k >= reducedInOne) {
         k -= reducedInOne;
      }

      for (int i = 0; i < len; i++) {
         if (chalk[i] > k)
            return i;
         else {
            k -= chalk[i];
         }
      }
      return -1;
   }

}
