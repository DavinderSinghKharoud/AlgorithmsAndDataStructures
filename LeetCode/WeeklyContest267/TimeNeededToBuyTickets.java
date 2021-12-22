package LeetCode.WeeklyContest267;

public class TimeNeededToBuyTickets {
   public static void main(String[] args) {
      TimeNeededToBuyTickets o = new TimeNeededToBuyTickets();
   }

   public int timeRequiredToBuy(int[] tickets, int k) {
      int len = tickets.length;
      int ans = 0;
      while (tickets[k] > 0) {
         for (int i = 0; i < len; i++) {
            if (tickets[i] > 0) {
               tickets[i]--;
               ans++;
            }
            if (i == k && tickets[i] == 0)
               break;
         }
      }
      return ans;
   }
}
