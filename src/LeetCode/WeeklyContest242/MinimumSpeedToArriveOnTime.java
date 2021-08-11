package LeetCode.WeeklyContest242;

public class MinimumSpeedToArriveOnTime {
    public static void main(String[] args) {
        MinimumSpeedToArriveOnTime obj = new MinimumSpeedToArriveOnTime();
        System.out.println(obj.minSpeedOnTime(new int[]{1,1,100000}, 2.01));
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        int start = 1, end = Integer.MAX_VALUE;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isPossible(mid, dist, hour)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (isPossible(end, dist, hour))
            return end;
        return -1;
    }

    boolean isPossible(int speed, int[] dist, double hour) {
        double hours = 0;
        for (int i = 0; i < dist.length; i++) {
            int dis = dist[i];
            double curr = (double) dis / speed;
            if (i != dist.length - 1) {
                curr = Math.ceil(curr);
            }
            hours += curr;
        }
        return hours <= hour;
    }
}
