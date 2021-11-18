package Preparation;

import java.util.*;

/**
 * In a tennis tournament of N players every player plays with every other player. The following condition always hold- If player P1 has won the match with P2 and player P2 has won from P3, then Player P1 has also defeated P3. Find winner of tournament in O(N) time and O(1) space. Find rank of players in O(NlogN) time.
 */
public class TennisTournament {

    public static void main(String[] args) {

        new TennisTournament().printRanking(new boolean[][]{
                {false, false, true, true, true, false, true},
                {true, false, true, true, true, true, true},
                {false, false, false, true, true, false, false},
                {false, false, false, false, true, false, false},
                {false, false, false, false, false, false, false},
                {true, false, true, true, true, false, true},
                {false, false, true, true, true, false, false}
        });
    }

    void printRanking(boolean[][] tournament) {
        List<Integer> players = new ArrayList<>();
        for (int i = 0; i < tournament.length; i++) players.add(i);
        players.sort((o1, o2) -> Boolean.compare(tournament[o2][o1], tournament[o1][o2]));

        players.forEach(o -> System.out.println(o + 1));
    }


    int findWinnder(boolean[][] tournament) {
        int winner = 0;
        for (int player = 1; player < tournament.length; player++) {
            if (tournament[player][winner]) winner = player;
        }

        return winner;
    }
}
