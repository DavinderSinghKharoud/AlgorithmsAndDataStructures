package ProjectEuler;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class LargeSum {

    private static final int SIZE = 100;

    public static void main(String[] args) {
        BigInteger[] numbers = readData("MinimizeDifference:\\Users\\SHARMA\\IdeaProjects\\AlgorithmsAndDataStructures\\src\\ProjectEuler\\euler12");
        BigInteger sum = BigInteger.ZERO;
        for(int i = 0; i < numbers.length; ++i)
            sum = sum.add(numbers[i]);
        String sumStr = sum.toString().substring(0,10);

        System.out.println(sumStr + " is first ten digits.");
    }

    private static BigInteger[] readData(String filename) {
        BigInteger[] nums = new BigInteger[SIZE];
        try {
            Scanner input = new Scanner(new File(filename));
            for(int i = 0; input.hasNext(); i++) {
                nums[i] = new BigInteger(input.next());
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("cannot find file " + filename);
        }



        return nums;
    }
}