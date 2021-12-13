package LeetCode.WeeklyContest271;

import java.util.*;

/**
 * There are n rings and each ring is either red, green, or blue. The rings are distributed across ten rods labeled from 0 to 9.
 * <p>
 * You are given a string rings of length 2n that describes the n rings that are placed onto the rods. Every two characters in rings forms a color-position pair that is used to describe each ring where:
 * <p>
 * The first character of the ith pair denotes the ith ring's color ('R', 'G', 'B').
 * The second character of the ith pair denotes the rod that the ith ring is placed on ('0' to '9').
 * For example, "R3G2B1" describes n == 3 rings: a red ring placed onto the rod labeled 3, a green ring placed onto the rod labeled 2, and a blue ring placed onto the rod labeled 1.
 * <p>
 * Return the number of rods that have all three colors of rings on them.
 */
public class RingsAndRods {
    public static void main(String[] args) {
        RingsAndRods o = new RingsAndRods();
    }

    public int countPoints(String rings) {
        Set<Integer> blue = new HashSet<>(), red = new HashSet<>(), green = new HashSet<>();

        for (int i = 0; i < rings.length(); i += 2) {
            char c = rings.charAt(i);
            int index = rings.charAt(i + 1) - '0';

            if (c == 'R') red.add(index);
            else if (c == 'G') green.add(index);
            else blue.add(index);
        }
        int count = 0;
        for (int num : red) {
            if (blue.contains(num) && green.contains(num)) count++;
        }
        return count;
    }
}
