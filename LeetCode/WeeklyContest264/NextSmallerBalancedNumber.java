package LeetCode.WeeklyContest264;

public class NextSmallerBalancedNumber {
   public static void main(String[] args) {
      NextSmallerBalancedNumber o = new NextSmallerBalancedNumber();

   }

   public int nextBeautifulNumber(int n) {
      n++;
      while(!isValid(n)){
         n++;
      }
      return n;
   }

   boolean isValid(int n){
      int[] count = new int[10];
      while(n > 0){
         int last = n % 10;
         count[last]++;
         n /= 10;
      }

      for(int i = 0; i < count.length; i++){
         if(count[i] > 0 && count[i] != i){ return false;
         }
      }
      return true;
   }
}
