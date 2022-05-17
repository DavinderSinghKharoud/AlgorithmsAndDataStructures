package LeetCode;

public class AddMultiplyExpression {

   public static void main(String[] args) {
      System.out.println(new AddMultiplyExpression().evaluate("4*2+1*2"));
   }

   int evaluate(String expression) {
      int len = expression.length();
      int prev = Integer.MIN_VALUE;
      int total = 0;
      int zero = '0';

      for (int i = 0; i < len; i++) {
         char c = expression.charAt(i);
         if (prev == Integer.MIN_VALUE) {
            int num = 0;
            while (i < len && Character.isDigit(expression.charAt(i))) {
               num = num * 10 + (expression.charAt(i) - zero);
               i++;
            }
            prev = num;
            total += num;
         } else if (c == '+') {
            i++;
            int nextNum = 0;
            while (i < len && Character.isDigit(expression.charAt(i))) {
               nextNum = nextNum * 10 + (expression.charAt(i) - zero);
               i++;
            }
            prev = nextNum;
            total += nextNum;
         } else {
            // multiply
            i++;
            int nextNum = 0;
            while (i < len && Character.isDigit(expression.charAt(i))) {
               nextNum = nextNum * 10 + (expression.charAt(i) - zero);
               i++;
            }

            total -= prev;
            int mul = prev * nextNum;
            total += mul;

            prev = mul;
         }
         i--;
      }
      return total;
   }
}
