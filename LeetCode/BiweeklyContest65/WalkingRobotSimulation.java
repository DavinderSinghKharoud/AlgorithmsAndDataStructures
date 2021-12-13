package LeetCode.BiweeklyContest65;

import java.util.*;

/**
 * User Accepted: 786
 * User Tried: 2440
 * Total Accepted: 797
 * Total Submissions: 9303
 * Difficulty: Medium
 * RingsAndRods width x height grid is on an XY-plane with the bottom-left cell at (0, 0) and the top-right cell at (width - 1, height - 1). The grid is aligned with the four cardinal directions ("North", "East", "South", and "West"). RingsAndRods robot is initially at cell (0, 0) facing direction "East".
 *
 * The robot can be instructed to move for a specific number of steps. For each step, it does the following.
 *
 * Attempts to move forward one cell in the direction it is facing.
 * If the cell the robot is moving to is out of bounds, the robot instead turns 90 degrees counterclockwise and retries the step.
 * After the robot finishes moving the number of steps required, it stops and awaits the next instruction.
 *
 * Implement the Robot class:
 *
 * Robot(int width, int height) Initializes the width x height grid with the robot at (0, 0) facing "East".
 * void step(int num) Instructs the robot to move forward num steps.
 * int[] getPos() Returns the current cell the robot is at, as an array of length 2, [x, y].
 * String getDir() Returns the current direction of the robot, "North", "East", "South", or "West".
 *
 *
 * Example 1:
 *
 * example-1
 * Input
 * ["Robot", "move", "move", "getPos", "getDir", "move", "move", "move", "getPos", "getDir"]
 * [[6, 3], [2], [2], [], [], [2], [1], [4], [], []]
 * Output
 * [null, null, null, [4, 0], "East", null, null, null, [1, 2], "West"]
 *
 * Explanation
 * Robot robot = new Robot(6, 3); // Initialize the grid and the robot at (0, 0) facing East.
 * robot.move(2);  // It moves two steps East to (2, 0), and faces East.
 * robot.move(2);  // It moves two steps East to (4, 0), and faces East.
 * robot.getPos(); // return [4, 0]
 * robot.getDir(); // return "East"
 * robot.move(2);  // It moves one step East to (5, 0), and faces East.
 *                 // Moving the next step East would be out of bounds, so it turns and faces North.
 *                 // Then, it moves one step North to (5, 1), and faces North.
 * robot.move(1);  // It moves one step North to (5, 2), and faces North (not West).
 * robot.move(4);  // Moving the next step North would be out of bounds, so it turns and faces West.
 *                 // Then, it moves four steps West to (1, 2), and faces West.
 * robot.getPos(); // return [1, 2]
 * robot.getDir(); // return "West"
 *
 *
 *
 * Constraints:
 *
 * 2 <= width, height <= 100
 * 1 <= num <= 105
 * At most 104 calls in total will be made to step, getPos, and getDir.
 */
public class WalkingRobotSimulation {
    public static void main(String[] args) {
        WalkingRobotSimulation o = new WalkingRobotSimulation(6, 3);
//        o.step(66392);
//        o.step(83376);
//        o.step(71796);
//        o.step(57514);
//        o.step(36284);
//        o.step(69866);
//        o.step(31652);
//        o.step(32038);
        o.step(2);
        o.step(2);
        System.out.println(Arrays.toString(o.getPos()));
        System.out.println(o.getDir());
        o.step(2);
        o.step(1);
        o.step(4);
        System.out.println(Arrays.toString(o.getPos()));
        System.out.println(o.getDir());
    }

    String[] dir = new String[]{"East", "North", "West", "South"};
    int w, h;
    int[] curr = new int[]{0, 0};
    int currDir = 0;
    String dirString = dir[currDir];
    int total = 0;

    public WalkingRobotSimulation(int width, int height) {
        w = width;
        h = height;
        total += Math.max(0, width - 1);
        total += Math.max(0, width - 1);
        total += Math.max(0, height - 1);
        total += Math.max(0, height - 1);
    }

    public void step(int num) {
        int step = num;
        step %= total;
        if (step == 0 && curr[0] == 0 && curr[1] == 0) {
            dirString = dir[3];
        }
        while (step != 0) {
            int a = curr[0], b = curr[1];
            int[] mod = getNextDir(a, b);
            // check if it is possible
            if (currDir == 0) {
                if (mod[1] >= w) {
                    // It is not possible
                    currDir = (currDir + 1) % 4;
                }
            } else if (currDir == 1) {
                if (mod[0] >= h) {
                    // It is not possible
                    currDir = (currDir + 1) % 4;
                }
            } else if (currDir == 2) {
                if (mod[1] < 0) {
                    // It is not possible
                    currDir = (currDir + 1) % 4;
                }
            } else {
                if (mod[0] < 0) {
                    // It is not possible
                    currDir = (currDir + 1) % 4;
                }
            }
            dirString = dir[currDir];
            mod = getNextDir(a, b);
            curr = mod;
            step--;
        }
    }

    int[] getNextDir(int a, int b) {
        if (currDir == 0) {
            b++;
        } else if (currDir == 1) {
            a++;
        } else if (currDir == 2) {
            b--;
        } else
            a--;
        return new int[]{a, b};
    }

    public int[] getPos() {
        return new int[]{curr[1], curr[0]};
    }

    public String getDir() {
        return dirString;
    }
}
