package Java;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PatternSyntaxChecker {

    public static void main(String[] args) {

        String pattern = "([A-Z])(.+)";

        try {
            Pattern.compile(pattern);
            System.out.println("Valid");
        }catch (PatternSyntaxException ex){
            System.out.println("Invalid");
        }
    }
}
