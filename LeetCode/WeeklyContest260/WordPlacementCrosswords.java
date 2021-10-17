package LeetCode.WeeklyContest260;

public class WordPlacementCrosswords {
    public static void main(String[] args) {
        WordPlacementCrosswords o = new WordPlacementCrosswords();
//        System.out.println(o.placeWordInCrossword(new char[][]{{'#', '#', '#', ' '},
//                {' ', 'a', 'a', 'b'}, {'#', 'b', ' ', ' '},
//                {'#', 'd', ' ', ' '}
//        }, "ab"));
        System.out.println(o.placeWordInCrossword(new char[][]{{'#', 'a', '#'},
                {' ', ' ', '#'}, {'#', 'c', ' '}
        }, "dbc"));
    }

    int n, m, len;
    String s;
    char[][] board;

    public boolean placeWordInCrossword(char[][] board, String word) {
        this.board = board;
        len = word.length();
        s = word;
        n = board.length;
        m = board[0].length;
        StringBuilder sbr = new StringBuilder(word);
        String reverse = sbr.reverse().toString();
        // Check verticals
        for (int i = 0; i < m; i++) {
            if (isPossVertical(i, word) || isPossVertical(i, reverse))
                return true;
        }

        for (int i = 0; i < n; i++) {
            if (isPossHorizontal(i, word) || isPossHorizontal(i, reverse))
                return true;
        }
        return false;
    }

    boolean isPossHorizontal(int row, String word) {
        int index = 0;

        for (int col = 0; col < m; col++) {
            char c = board[row][col];
            if (c == '#') {
                index = 0;
            } else {
                if (index == 0) {
                    // start of the word
                    if ((col - 1 < 0 || board[row][col - 1] == '#') && (c == ' ' || c == word.charAt(index))) {
                        index++;
                    }
                } else {
                    if (c == word.charAt(index) || c == ' ') {
                        index++;
                    } else
                        index = 0;
                }
            }

            if (index == word.length()) {
                // Check bottom
                if (col + 1 >= m || board[row][col + 1] == '#')
                    return true;
                index = 0;
            }
        }
        return false;
    }

    boolean isPossVertical(int col, String word) {
        int index = 0;

        for (int row = 0; row < n; row++) {
            char c = board[row][col];
            if (c == '#') {
                index = 0;
            } else {
                if (index == 0) {
                    // start of the word
                    if ( (row - 1 < 0 || board[row - 1][col] == '#') && (c == ' ' || c == word.charAt(index))) {
                        index++;
                    }
                } else {
                    if (c == word.charAt(index) || c == ' ') {
                        index++;
                    } else
                        index = 0;
                }
            }

            if (index == word.length()) {
                // Check bottom
                if (row + 1 >= n || board[row + 1][col] == '#')
                    return true;
                index = 0;
            }
        }
        return false;
    }
}
