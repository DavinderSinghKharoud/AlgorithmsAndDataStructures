package algorithms.InterviewBit;

/**
 * Please Note:
 *
 * There are certain questions where the interviewer would intentionally frame the question vague.
 * The expectation is that you will ask the correct set of clarifications or state your assumptions before you jump into coding.
 *
 * Implement atoi to convert a string to an integer.
 *
 * Example :
 *
 * Input : "9 2704"
 * Output : 9
 * Note: There might be multiple corner cases here. Clarify all your doubts using “See Expected Output”.
 *
 *  Questions: Q1. Does string contain whitespace characters before the number?
 * A. Yes Q2. Can the string have garbage characters after the number?
 * A. Yes. Ignore it. Q3. If no numeric character is found before encountering garbage characters, what should I do?
 * A. Return 0. Q4. What if the integer overflows?
 * A. Return INT_MAX if the number is positive, INT_MIN otherwise.
 * Warning : DO NOT USE LIBRARY FUNCTION FOR ATOI.
 * If you do, we will disqualify your submission retroactively and give you penalty points.
 */
public class Atoi {


    public static int atoi(final String s) {

        int len = s.length();
        if (len == 0) return 0;
        boolean isPositive = false;
        boolean isNegative = false;
        StringBuilder sbr = new StringBuilder();

        for (int index = 0; index < len; index++) {

            char c = s.charAt(index);

            if (c == '+') {
                if ( isNegative && sbr.length() == 0) {
                    return 0;
                } else if (!isPositive) {
                    isPositive = true;
                    continue;
                } else if (sbr.length() != 0) {
                    break;
                }else if( isPositive && sbr.length() == 0){
                    return 0;
                }
            } else if (c == '-') {
                if (isPositive && sbr.length() == 0) {
                    return 0;
                } else if (!isNegative) {
                    isNegative = true;
                    continue;
                } else if (sbr.length() != 0) {
                    break;
                } else if (isNegative) {
                    return 0;
                }
            }
            if (c != ' ') {
                boolean isDigit = Character.isDigit(c);
                if (!isDigit && sbr.length() == 0) {
                    return 0;
                } else if (!isDigit) {
                    break;
                } else {
                    sbr.append(c);
                }

            } else if (sbr.length() != 0) {
                break;
            } else if (isPositive || isNegative) {
                return 0;
            }
        }

        if( isInt(sbr.toString() ) ){
            if( isPositive || !isNegative){
                return Integer.parseInt(sbr.toString());
            }else {
                return -1 * Integer.parseInt(sbr.toString());
            }
        }else{
            if( isPositive || !isNegative){
                return Integer.MAX_VALUE;
            }else{
                return Integer.MIN_VALUE;
            }
        }
    }

    private static boolean isInt( String num ){
        try{
            Integer.parseInt(num);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static void main(String[] args) {
        System.out.println(atoi2("88297 248252140B12"));
    }

    public static int atoi2(final String s) {

        StringBuilder sbr = new StringBuilder();
        boolean isNegative = false;
        boolean isNumberFound = false;

        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);

            if( isNumberFound ){
                if(c < '0' || c > '9'){
                    break;
                }else{
                    sbr.append(c);
                }

            }else{

                if( c == ' ' ) continue;
                else if( c == '-' ){
                    isNegative = true;
                    isNumberFound = true;
                }else if( c == '+' ){
                    isNegative = false;
                    isNumberFound = true;
                }else if( c < '0' || c > '9' ){
                    return 0;
                }else{
                    sbr.append(c);
                    isNumberFound = true;
                }
            }
        }


        if (sbr.length() == 0 ) return 0;
        if( isInt(sbr.toString() ) ){
            if( isNegative ){
                return -1 * Integer.parseInt(sbr.toString());
            }else {
                return Integer.parseInt(sbr.toString());
            }
        }else{
            if( isNegative){
                return Integer.MIN_VALUE;
            }else{
                return Integer.MAX_VALUE;
            }
        }

    }
}
