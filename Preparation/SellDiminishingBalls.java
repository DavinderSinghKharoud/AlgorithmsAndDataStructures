package Preparation;

import java.util.*;

/**
 * You have an inventory of different colored balls, and there is a customer that wants orders balls of any color.
 *
 * The customer weirdly values the colored balls. Each colored ball's value is the number of balls of that color you currently have in your inventory. For example, if you own 6 yellow balls, the customer would pay 6 for the first yellow ball. After the transaction, there are only 5 yellow balls left, so the next yellow ball is then valued at 5 (i.e., the value of the balls decreases as you sell more to the customer).
 *
 * You are given an integer array, inventory, where inventory[i] represents the number of balls of the ith color that you initially own. You are also given an integer orders, which represents the total number of balls that the customer wants. You can sell the balls in any order.
 *
 * Return the maximum total value that you can attain after selling orders colored balls. As the answer may be too large, return it modulo 109 + 7.
 */


public class SellDiminishingBalls {

    public static void main(String[] args) {
        System.out.println(new SellDiminishingBalls().maxProfit2(new int[]{
                701695700, 915736894, 35093938, 364836059, 452183894, 951826038, 861556610, 441929847, 842650446, 858413011, 457896886, 35119509, 776620034, 396643588, 83585103, 681609037
        }, 598226067));
    }

    int mod = (int) 1e9 + 7;

    public int maxProfit2(int[] inventory, int orders) {
        long ans = 0;

        int start = 0, end = 1000_000_000;
        //find the minimum row below which we can't make orders
        while (start < end) {
            int mid = start + (end - start) / 2; //inclusive
            if (isPoss(mid, inventory, orders)) {
                start = mid + 1;
            } else end = mid;
        }
        int lowerBound = end;
        //Get all the values that should be added to the ans
        for (int num : inventory) {
            if (num >= lowerBound) {
                ans = add(ans, getSum(lowerBound, num));
                orders -= (num - lowerBound + 1);
            }
        }
        //Add the remaining
        ans = add(ans, ((long) (lowerBound - 1) * orders));

        return (int) ans;
    }

    boolean isPoss(int mid, int[] inventory, int orders) {
        for (int num : inventory) {
            orders -= Math.max(0, num - mid + 1);
            if (orders <= 0) break;
        }
        return orders <= 0;
    }

    //TLE
    public int maxProfit(int[] inventory, int orders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int count : inventory) pq.add(count);
        long ans = 0;
        while (orders > 0) {
            int curr = pq.poll();
            if (pq.isEmpty()) {
                ans = add(ans, getSum(curr - orders + 1, curr));
                orders = 0;
            } else {
                int next = pq.peek();
                int poss = curr - next + 1;
                if (orders <= poss) {
                    poss = orders;
                    ans = add(ans, getSum(curr - poss + 1, curr));
                    orders = 0;
                } else {
                    ans = add(ans, getSum(curr - poss + 1, curr));
                    orders -= (poss);
                    if (curr - poss > 0) pq.add(curr - poss);
                }
            }
        }
        return (int) ans;
    }

    long getSum(long a, long b) {
        return (a + b) * (b - a + 1) / 2;
    }

    long add(long a, long b) {
        return (a + b) % mod;
    }
}
