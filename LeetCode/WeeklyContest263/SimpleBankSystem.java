package LeetCode.WeeklyContest263;

public class SimpleBankSystem {
   public static void main(String[] args) {
//      SimpleBankSystem o = new SimpleBankSystem();
   }

   long[] bank;

   public SimpleBankSystem(long[] balance) {
      bank = balance;
   }

   public boolean transfer(int account1, int account2, long money) {
      account1--;
      account2--;
      if (!isValid(account1) || !isValid(account2))
         return false;
      if (bank[account1] < money)
         return false;
      bank[account1] -= money;
      bank[account2] += money;
      return true;
   }

   public boolean deposit(int account, long money) {
      account--;
      if (!isValid(account))
         return false;
      bank[account] += money;
      return true;
   }

   public boolean withdraw(int account, long money) {
      account--;
      if (!isValid(account))
         return false;
      if (bank[account] < money)
         return false;
      bank[account] -= money;
      return true;
   }

   boolean isValid(int a) {
      return a >= 0 && a < bank.length;
   }
}
