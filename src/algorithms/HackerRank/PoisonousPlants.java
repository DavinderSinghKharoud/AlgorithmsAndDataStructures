package algorithms.HackerRank;

import java.util.*;

/**
 * There are a number of plants in a garden. Each of these plants has been treated with some amount of pesticide. After each day, if any plant has more pesticide than the plant on its left, being weaker than the left one, it dies.
 * You are given the initial values of the pesticide in each of the plants. Print the number of days after which no plant dies, i.e. the time after which there are no plants with more pesticide content than the plant to their left.
 * For example, pesticide levels . Using a -indexed array, day  plants  and  die leaving . On day , plant  of the current array dies leaving . As there is no plant with a higher concentration of pesticide than the one to its left, plants stop dying after day .
 * Function Description
 * Complete the function poisonousPlants in the editor below. It must return an integer representing the number of days until plants no longer die from pesticide.
 * poisonousPlants has the following parameter(s):
 * p: an array of integers representing pesticide levels in each plant
 * Input Format
 * The first line contains an integer , the size of the array .
 * The next line contains  space-separated integers .
 * Constraints
 *
 *
 * Output Format
 * Output an integer equal to the number of days after which no plants die.
 * Sample Input
 * 7
 * 6 5 8 4 7 10 9
 * Sample Output
 * 2
 * Explanation
 * Initially all plants are alive.
 * Plants = {(6,1), (5,2), (8,3), (4,4), (7,5), (10,6), (9,7)}
 * Plants[k] = (i,j) => jth plant has pesticide amount = i.
 * After the 1st day, 4 plants remain as plants 3, 5, and 6 die.
 * Plants = {(6,1), (5,2), (4,4), (9,7)}
 * After the 2nd day, 3 plants survive as plant 7 dies.
 * Plants = {(6,1), (5,2), (4,4)}
 * After the 2nd day the plants stop dying.
 */
public class PoisonousPlants {

    //Time complexity O( numberOfDays * numberOfplants) and Space complexity O(numberOf plants)
    static int poisonousPlants(int[] p) {

        int days = 0;
        boolean isAnyPlantDied = true;
        List<Integer> lst = new ArrayList<>();
        for (int num : p) {
            lst.add(num);
        }
        List<Integer> modifiedList = new ArrayList<>();

        while (isAnyPlantDied) {
            isAnyPlantDied = false;
            days++;
            for (int index = 0; index < lst.size(); index++) {
                if (index == 0) {
                    modifiedList.add(lst.get(index));
                    continue;
                }


                if (lst.get(index - 1) < lst.get(index)) {
                    isAnyPlantDied = true;
                    continue;
                }

                modifiedList.add(lst.get(index));


            }
            lst = new ArrayList<>(modifiedList);
            modifiedList = new ArrayList<>();


        }
        return days - 1;
    }

    public static void main(String[] args) {

        System.out.println(poisonousPlants2(new int[]{6, 5, 8, 4, 7, 10, 9}));
    }

    static int poisonousPlants2(int[] p) {
        Stack<int[]> stack = new Stack<>();
        int maxDays = 0;

        for (int num : p) {
            int days = 0;

            while (!stack.isEmpty() && stack.peek()[0] >= num) {
                days = Math.max(days, stack.pop()[1]);
            }

            if (!stack.isEmpty()) {
                days++;
            } else {
                days = 0;
            }

            maxDays = Math.max(maxDays, days);
            stack.push(new int[]{num, days});
        }

        return maxDays;
    }
}
