package algorthims.InterviewBit;

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 *
 * 1, 11, 21, 1211, 111221, ...
 * 1 is read off as one 1 or 11.
 * 11 is read off as two 1s or 21.
 *
 * 21 is read off as one 2, then one 1 or 1211.
 *
 * Given an integer n, generate the nth sequence.
 *
 * Note: The sequence of integers will be represented as a string.
 *
 * Example:
 *
 * if n = 2,
 * the sequence is 11.
 */
public class CountAndSay {

    public static String countAndSay(int n) {

        String previous = "1";
        if( n == 1 ) return previous;

        for (int index = 1; index < n; index++) {

            previous = createNext( previous );
        }

        return previous;
    }

    private static String createNext(String previous) {
        int count = 1;
        char previous_char = previous.charAt(0);
        StringBuilder sbr = new StringBuilder();

        for (int index = 1; index < previous.length(); index++) {
            char curr = previous.charAt(index);
            if( curr != previous_char ){
                sbr.append(count);
                sbr.append(previous_char);
                previous_char = curr;
                count = 1;
            }else{
                count++;
            }
        }

        sbr.append(count);
        sbr.append(previous_char);

        return sbr.toString();
    }

    public static void main(String[] args) {

        System.out.println( countAndSay(5));
    }
}
