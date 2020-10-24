package algorthims.InterviewBit;

import java.util.*;

/**
 * Rishabh takes out his Snakes and Ladders Game, stares the board and wonders: "If I can always roll the die to whatever number I want, what would be the least number of rolls to reach the destination?"
 * <p>
 * RULES:
 * <p>
 * The game is played with cubic dice of 6 faces numbered from 1 to A.
 * Starting from 1 , land on square 100 with the exact roll of the die. If moving the number rolled would place the player beyond square 100, no move is made.
 * If a player lands at the base of a ladder, the player must climb the ladder. Ladders go up only.
 * If a player lands at the mouth of a snake, the player must go down the snake and come out through the tail. Snakes go down only.
 * BOARD DESCRIPTION:
 * <p>
 * The board is always 10 x 10 with squares numbered from 1 to 100.
 * The board contains N ladders given in a form of 2D matrix A of size N * 2 where (A[i][0], A[i][1]) denotes a ladder that has its base on square A[i][0] and end at square A[i][1].
 * The board contains M snakes given in a form of 2D matrix B of size M * 2 where (B[i][0], B[i][1]) denotes a snake that has its mouth on square B[i][0] and tail at square B[i][1].
 * <p>
 * <p>
 * Problem Constraints
 * 1 <= N, M <= 15
 * <p>
 * 1 <= A[i][0], A[i][1], B[i][0], B[i][1] <= 100
 * <p>
 * A[i][0] < A[i][1] and B[i][0] > B[i][1]
 * <p>
 * Neither square 1 nor square 100 will be the starting point of a ladder or snake.
 * <p>
 * A square will have at most one endpoint from either a snake or a ladder.
 * <p>
 * <p>
 * <p>
 * Input Format
 * First argument is a 2D matrix A of size N * 2 where (A[i][0], A[i][1]) denotes a ladder that has its base on square A[i][0] and end at square A[i][1].
 * <p>
 * Second argument is a 2D matrix B of size M * 2 where (B[i][0], B[i][1]) denotes a snake that has its mouth on square B[i][0] and tail at square B[i][1].
 * <p>
 * <p>
 * <p>
 * Output Format
 * Return the least number of rolls to move from start to finish on a separate line. If there is no solution, return -1.
 * <p>
 * <p>
 * <p>
 * Example Input
 * Input 1:
 * <p>
 * A = [  [32, 62]
 * [42, 68]
 * [12, 98]
 * ]
 * B = [  [95, 13]
 * [97, 25]
 * [93, 37]
 * [79, 27]
 * [75, 19]
 * [49, 47]
 * [67, 17]
 * Input 2:
 * <p>
 * A = [  [8, 52]
 * [6, 80]
 * [26, 42]
 * [2, 72]
 * ]
 * B = [  [51, 19]
 * [39, 11]
 * [37, 29]
 * [81, 3]
 * [59, 5]
 * [79, 23]
 * [53, 7]
 * [43, 33]
 * [77, 21]
 * <p>
 * <p>
 * Example Output
 * Output 1:
 * <p>
 * 3
 * Output 2:
 * <p>
 * 5
 * <p>
 * <p>
 * Example Explanation
 * Explanation 1:
 * <p>
 * The player can roll a 5 and a 6 to land at square 12. There is a ladder to square 98. A roll of 2 ends the traverse in 3 rolls.
 * <p>
 * <p>
 * Explanation 2:
 * <p>
 * The player first rolls 5 and climbs the ladder to square 80. Three rolls of 6 get to square 98.
 * A final roll of 2 lands on the target square in 5 total rolls.
 */
public class SnakeLadderProblem {

    //O( 6 ^ 10 ) time complexity and O(n) space complexity
    public static int snakeLadder(int[][] l, int[][] s) {

        // we can use a HashMap to store ladders and snakes Although, it will not make a big difference
        Queue<Pair> queue = new LinkedList<>();
        int[] visited = new int[100];
        Arrays.fill(visited, Integer.MAX_VALUE);
        queue.add(new Pair(1, 0));

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();


            for (int count = 6; count >= 1; count--) {

                int nextPosition = curr.position + count;

                //Check the ladder
                nextPosition = onLadder(nextPosition, l);

                //Check the snake
                nextPosition = overSnake(nextPosition, s);

                if (nextPosition <= 100 && visited[nextPosition - 1] > curr.steps + 1) {
                    queue.add(new Pair(nextPosition, curr.steps + 1));
                    visited[nextPosition - 1] = curr.steps + 1;
                }
            }
        }

        return visited[99] == Integer.MAX_VALUE ? -1 : visited[99];

    }

    public static int overSnake(int curr, int[][] snakes) {

        for (int[] snake : snakes) {
            if (curr == snake[0]) {
                return snake[1];
            }
        }
        return curr;
    }

    public static int onLadder(int curr, int[][] ladders) {

        for (int[] ladder : ladders) {
            if (curr == ladder[0]) {
                return ladder[1];
            }
        }
        return curr;
    }

    public static void main(String[] args) {

        int[][] ladders = new int[][]{{3, 54}, {37, 100}};
        int[][] snakes = new int[][]{{56, 33}};

        System.out.println(snakeLadder(ladders, snakes));

    }

    static class Pair {
        int position;
        int steps;

        public Pair(int position, int steps) {
            this.position = position;
            this.steps = steps;
        }
    }
}
