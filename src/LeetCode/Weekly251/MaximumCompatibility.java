package LeetCode.Weekly251;

public class MaximumCompatibility {
    public static void main(String[] args) {
        MaximumCompatibility o = new MaximumCompatibility();

        System.out.println(o.maxCompatibilitySum(new int[][]{
                {1,1,0}, {1, 0, 1}, {0, 0, 1}
        }, new int[][]{
                {1, 0, 0}, {0, 0, 1}, {1, 1, 0}
        }));
    }

    int ans = 0;
    int[][] mentors, students;

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int[] prem = new int[students.length];
        this.mentors = mentors;
        this.students = students;
        for (int i = 0; i < students.length; i++) prem[i] = i;
        get(prem, 0);
        return ans;
    }


    private void get(int[] nums, int start) {
        if (start == nums.length - 1) {
            //Check the answer
            ans = Math.max(ans, checkAns(nums));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            int tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;
            get(nums, start + 1);
            nums[i] = nums[start];
            nums[start] = tmp;
        }
    }

    private int checkAns(int[] nums) {
        int score = 0;
        for (int i = 0; i < mentors.length; i++) {
            int[] student = students[nums[i]];
            int[] mentor = mentors[i];
            for (int j = 0; j < student.length; j++) {
                if (student[j] == mentor[j]) score++;
            }
        }
        return score;
    }
}
