package algorithms.LeetCode.Mock;

public class BullsAndCows {


    public static String getHint(String secret, String guess) {

        int[] dp1 = new int[10];
        int[] dp2 = new int[10];
        int len = secret.length();
        int bulls = 0;
        int cows = 0;

        for (int index = 0; index < len; index++) {
            char c1 = secret.charAt(index);
            char c2 = guess.charAt(index);

            if( c1 == c2 ) bulls++;
            else {
                dp1[c1 - 48]++;
                dp2[c2 - 48]++;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(bulls).append("A");

        for (int count = 0; count < 10; count++) {
            cows += Math.min( dp1[count], dp2[count] );
        }

        stringBuilder.append(cows).append("B");
        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        System.out.println( getHint("1123", "0111" ));
    }
}
