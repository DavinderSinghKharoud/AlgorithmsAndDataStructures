package LeetCode.WeeklyContest274;

public class NumberLastBeamsBank {
   public static void main(String[] args) {
      NumberLastBeamsBank o = new NumberLastBeamsBank();
       System.out.println(o.numberOfBeams(new String[]{
               "011001","000000","010100","001000"
       }));
   }

   public int numberOfBeams(String[] bank) {
      int len = bank.length;
      int count = 0;
      int ans = 0;
      for (int i = 0; i < len; i++) {
         int curr = 0;
         for (int j = 0; j < bank[i].length(); j++) {
            char c = bank[i].charAt(j);
            if (c == '1')
               curr++;
         }
         ans += (curr * count);
         if (curr != 0)
            count = curr;
      }
      return ans;
   }
}
