package LeetCode;

/**
 * Given a column title as appear in an Excel sheet, return its corresponding
 * column number.
 *
 * For example:
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 */
public class ExcelSheetColumnNumber {

    public static int titleToNumber(String s) {
        int sum = 0;
        int n = s.length();
        for(int i=n-1;i>=0;i--){
            int num = s.charAt(i)-'A'+1;
            sum+=num*(Math.pow(26,(n-1-i)));
        }
        return sum;
    }

    public static void main(String[] args) {

        System.out.println( titleToNumber("ZY"));
    }
}
