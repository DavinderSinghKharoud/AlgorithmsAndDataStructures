package LeetCode.BiweeklyContest71;

/**
 * CountingWordsPrefix generic microwave supports cooking times for:
 *
 * at least 1 second.
 * at most 99 minutes and 99 seconds.
 * To set the cooking time, you push at most four digits. The microwave normalizes what you push as four digits by prepending zeroes. It interprets the first two digits as the minutes and the last two digits as the seconds. It then adds them up as the cooking time. For example,
 *
 * You push 9 5 4 (three digits). It is normalized as 0954 and interpreted as 9 minutes and 54 seconds.
 * You push 0 0 0 8 (four digits). It is interpreted as 0 minutes and 8 seconds.
 * You push 8 0 9 0. It is interpreted as 80 minutes and 90 seconds.
 * You push 8 1 3 0. It is interpreted as 81 minutes and 30 seconds.
 * You are given integers startAt, moveCost, pushCost, and targetSeconds. Initially, your finger is on the digit startAt. Moving the finger above any specific digit costs moveCost units of fatigue. Pushing the digit below the finger once costs pushCost units of fatigue.
 *
 * There can be multiple ways to set the microwave to cook for targetSeconds seconds but you are interested in the way with the minimum cost.
 *
 * Return the minimum cost to set targetSeconds seconds of cooking time.
 *
 * Remember that one minute consists of 60 seconds
 */
public class MinCostCookingTime {
    public static void main(String[] args) {
        MinCostCookingTime o = new MinCostCookingTime();
        System.out.println(o.minCostSetTime(1, 2, 1, 600));
    }

    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int ans = Integer.MAX_VALUE;
        int maxMinutes = targetSeconds / 60;
        for (int min = 0; min <= maxMinutes; min++) {
            int remainingSeconds = targetSeconds - min * 60;
            if (remainingSeconds > 99 || min > 99) continue;
            ans = Math.min(ans, findCost(startAt, moveCost, pushCost, min, remainingSeconds));
        }
        return ans;
    }

    private int findCost(int startAt, int moveCost, int pushCost, int min, int remainingSeconds) {
        String target = String.valueOf(min * 100 + remainingSeconds);
        char curr = (char) (startAt + '0');
        int cost = 0;
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            if (c == curr) cost += pushCost;
            else {
                cost += moveCost + pushCost;
                curr = c;
            }
        }
        return cost;
    }
}
