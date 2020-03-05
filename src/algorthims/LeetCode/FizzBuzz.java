package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FizzBuzz {

    public static List<String> fizzBuzz(int n) {

        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {

            if (i % 3 == 0 && i % 5 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(i + "");
            }


        }

        return result;
    }


        public List<String> fizzBuzz2(int n) {

            // ans list
            List<String> ans = new ArrayList<String>();

            // Hash map to store all fizzbuzz mappings.
            HashMap<Integer, String> fizzBizzDict =
                    new HashMap<Integer, String>() {
                        {
                            put(3, "Fizz");
                            put(5, "Buzz");
                        }
                    };

            for (int num = 1; num <= n; num++) {

                String numAnsStr = "";

                for (Integer key : fizzBizzDict.keySet()) {

                    // If the num is divisible by key,
                    // then add the corresponding string mapping to current numAnsStr
                    if (num % key == 0) {
                        numAnsStr += fizzBizzDict.get(key);
                    }
                }

                if (numAnsStr.equals("")) {
                    // Not divisible by 3 or 5, add the number
                    numAnsStr += Integer.toString(num);
                }

                // Append the current answer str to the ans list
                ans.add(numAnsStr);
            }

            return ans;
        }


    public static void main(String[] args) {

        System.out.println(fizzBuzz(15));
    }
}
