/*
As it is Tushar’s Birthday on March 1st, he decided to throw a party to all his friends at TGI Fridays in Pune.
Given are the eating capacity of each friend, filling capacity of each dish and cost of each dish. A friend is satisfied if the sum of the filling capacity of dishes he ate is equal to his capacity. Find the minimum cost such that all of Tushar’s friends are satisfied (reached their eating capacity).

NOTE:

Each dish is supposed to be eaten by only one person. Sharing is not allowed.
Each friend can take any dish unlimited number of times.
There always exists a dish with filling capacity 1 so that a solution always exists.
Input Format

Friends : List of integers denoting eating capacity of friends separated by space.
Capacity: List of integers denoting filling capacity of each type of dish.
Cost :    List of integers denoting cost of each type of dish.
Constraints:
1 <= Capacity of friend <= 1000
1 <= No. of friends <= 1000
1 <= No. of dishes <= 1000

Example:

Input:
    2 4 6
    2 1 3
    2 5 3

Output:
    14

Explanation: 
    First friend will take 1st and 2nd dish, second friend will take 2nd dish twice.  Thu
 */

import java.util.*;

public class TusharBirthdayParty {

    static int min = Integer.MAX_VALUE;

    //Brute Force (Time limit exceeded)
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public static int solve(final List<Integer> friends, final List<Integer> weights, final List<Integer> costs) {

        helper(friends, weights, costs, 0, 0, 0, 0);
        return min;
    }

    public static void helper(List<Integer> friends, List<Integer> weights, List<Integer> costs, int index, int weight, int friend, int total) {

        if (friend >= friends.size()) {
            min = Math.min(total, min);
            return;
        }

        if (index >= costs.size()) {
            return;
        }
        if (weight > friends.get(friend)) {
            return;
        }
        if (weight == friends.get(friend)) {
            helper(friends, weights, costs, 0, 0, friend + 1, total);
        }

        helper(friends, weights, costs, index + 1, weight, friend, total);
        helper(friends, weights, costs, index, weight + weights.get(index), friend, total + costs.get(index));
    }

    public static void main(String[] args) {

        List<Integer> friends = new ArrayList<>();
        friends.add(4);
        friends.add(6);

        List<Integer> weights = new ArrayList<>();
        weights.add(1);
        weights.add(3);

        List<Integer> costs = new ArrayList<>();
        costs.add(5);
        costs.add(3);

        System.out.println(solve2(friends, weights, costs));


    }


    public static int solve2(final List<Integer> friends, final List<Integer> weights, final List<Integer> costs) {
        int max = friends.get(0);
        for (int friend : friends) {
            max = Math.max(max, friend);
        }

        int[] dp = new int[max + 1];


        for (int index = 1; index <= max; index++) {
            int min = Integer.MAX_VALUE;
            for (int weight_index = 0; weight_index < weights.size(); weight_index++) {
                if (index - weights.get(weight_index) >= 0) {
                    min = Math.min(min, dp[index - weights.get(weight_index)] + costs.get(weight_index));
                }
            }

            dp[index] = min;
        }

        int min_cost = 0;
        for (int friend : friends) {
            min_cost += dp[friend];
        }
        return min_cost;
    }
}

