package Algorithms.LeetCode.WeeklyContest243;

/**
 * You are given an integer hoursBefore, the number of hours you have to travel to your meeting. To arrive at your meeting, you have to travel through n roads. The road lengths are given as an integer array dist of length n, where dist[i] describes the length of the ith road in kilometers. In addition, you are given an integer speed, which is the speed (in km/h) you will travel at.
 *
 * After you travel road i, you must rest and wait for the next integer hour before you can begin traveling on the next road. Note that you do not have to rest after traveling the last road because you are already at the meeting.
 *
 * For example, if traveling a road takes 1.4 hours, you must wait until the 2 hour mark before traveling the next road. If traveling a road takes exactly 2 hours, you do not need to wait.
 * However, you are allowed to skip some rests to be able to arrive on time, meaning you do not need to wait for the next integer hour. Note that this means you may finish traveling future roads at different hour marks.
 *
 * For example, suppose traveling the first road takes 1.4 hours and traveling the second road takes 0.6 hours. Skipping the rest after the first road will mean you finish traveling the second road right at the 2 hour mark, letting you start traveling the third road immediately.
 * Return the minimum number of skips required to arrive at the meeting on time, or -1 if it is impossible.
 *
 *
 *
 * Example 1:
 *
 * Input: dist = [1,3,2], speed = 4, hoursBefore = 2
 * Output: 1
 * Explanation:
 * Without skipping any rests, you will arrive in (1/4 + 3/4) + (3/4 + 1/4) + (2/4) = 2.5 hours.
 * You can skip the first rest to arrive in ((1/4 + 0) + (3/4 + 0)) + (2/4) = 1.5 hours.
 * Note that the second rest is shortened because you finish traveling the second road at an integer hour due to skipping the first rest.
 */
public class MinimumSkipsToArriveAtMeetingOnTime {
    public static void main(String[] args) {
        MinimumSkipsToArriveAtMeetingOnTime o = new MinimumSkipsToArriveAtMeetingOnTime();
        System.out.println(o.minSkips(new int[]{7, 3, 5, 5}, 2, 10));
    }

    public int minSkips(int[] dist, int speed, int hoursBefore) {
        int len = dist.length;
        double[][] dp = new double[len][len];

        for (int road = 0; road < len; road++) {
            for (int skip = 0; skip <= road; skip++) {
                if (road == 0 && skip == 0) {
                    dp[road][skip] = (double) dist[road] / speed;
                } else if (skip == 0) {
                    dp[road][0] = Math.ceil(dp[road - 1][0] - 0.00000001) + (double) dist[road] / speed;
                } else if (road == skip) {
                    //At the end
                    dp[road][skip] = dp[road - 1][skip - 1] + (double) dist[road] / speed;
                } else {
                    dp[road][skip] = Math.min(Math.ceil(dp[road - 1][skip] - 0.00000001), dp[road - 1][skip - 1]) + (double) dist[road] / speed;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            if (dp[len - 1][i] <= hoursBefore) return i;
        }
        return -1;
    }
}
