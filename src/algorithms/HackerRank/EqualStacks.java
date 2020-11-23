package algorithms.HackerRank;

import java.util.*;

/**
 * You have three stacks of cylinders where each cylinder has the same diameter, but they may vary in height. You can change the height of a stack by removing and discarding its topmost cylinder any number of times.
 * Find the maximum possible height of the stacks such that all of the stacks are exactly the same height. This means you must remove zero or more cylinders from the top of zero or more of the three stacks until they're all the same height, then print the height. The removals must be performed in such a way as to maximize the height.
 * Note: An empty stack is still a stack.
 * Input Format
 * The first line contains three space-separated integers, , , and , describing the respective number of cylinders in stacks , , and . The subsequent lines describe the respective heights of each cylinder in a stack from top to bottom:
 * The second line contains  space-separated integers describing the cylinder heights in stack . The first element is the top of the stack.
 * The third line contains  space-separated integers describing the cylinder heights in stack . The first element is the top of the stack.
 * The fourth line contains  space-separated integers describing the cylinder heights in stack . The first element is the top of the stack.
 * Constraints
 *
 *
 * Output Format
 * Print a single integer denoting the maximum height at which all stacks will be of equal height.
 * Sample Input
 * 5 3 4
 * 3 2 1 1 1
 * 4 3 2
 * 1 1 4 1
 * Sample Output
 * 5
 * Explanation
 * Initially, the stacks look like this:
 * initial stacks
 * Observe that the three stacks are not all the same height. To make all stacks of equal height, we remove the first cylinder from stacks  and , and then remove the top two cylinders from stack  (shown below).
 * modified stacks
 * As a result, the stacks undergo the following change in height:
 *
 *
 *
 * All three stacks now have . Thus, we print  as our answer.
 */
public class EqualStacks {

    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {


        int total1 = getTotal(h1);
        int total2 = getTotal(h2);
        int total3 = getTotal(h3);

        int index1 = 0, index2 = 0, index3 = 0;

        while (true) {
            if (h1.size() == 0 || h2.size() == 0 || h3.size() == 0) {
                return 0;
            }

            if (total1 == total2 && total1 == total3) return total1;

            //Finding the stack with maximum sum and removing its element
            if (total1 >= total2 && total1 >= total3) {
                total1 -= h1.get(index1++);
            } else if (total2 >= total3) {
                total2 -= h2.get(index2++);
            } else {
                total3 -= h3.get(index3++);
            }
        }
    }

    public static Integer getTotal(List<Integer> lst) {
        return lst.stream().mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(3);
        list1.add(2);
        list1.add(1);
        list1.add(1);
        list1.add(1);

        List<Integer> list2 = new ArrayList<>();
        list2.add(4);
        list2.add(3);
        list2.add(2);

        List<Integer> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(1);
        list3.add(4);
        list3.add(1);

        System.out.println(equalStacks(list1, list2, list3));
    }
}
