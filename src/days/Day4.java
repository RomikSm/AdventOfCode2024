package days;

import java.util.List;

public class Day4 {

    private static final String XMAS = "XMAS";
    private static final int[] DX = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] DY = {-1, 0, 1, -1, 1, -1, 0, 1};


    public static void partOne() {
        List<String> list = Helper.readLines("day4.txt");
        int result = countXMAS(list);
        System.out.println(result);
    }

    private static int countXMAS(List<String> list) {
        if (list.isEmpty()) {
            return 0;
        }

        int rows = list.size();
        int cols = list.getFirst().length();
        int count = 0;

        char[][] board = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            board[i] = list.get(i).toCharArray();
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == XMAS.charAt(0)) {
                    for (int dir = 0; dir < 8; dir++) {
                        if (checkDirection(board, i, j, dir)) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private static boolean checkDirection(char[][] board, int startRow, int startCol, int direction) {
        int rows = board.length;
        int cols = board[0].length;

        int endRow = startRow + DX[direction] * (XMAS.length() - 1);
        int endCol = startCol + DY[direction] * (XMAS.length() - 1);

        if (endRow < 0 || endRow >= rows || endCol < 0 || endCol >= cols) {
            return false;
        }

        for (int i = 0; i < XMAS.length(); i++) {
            int currentRow = startRow + DX[direction] * i;
            int currentCol = startCol + DY[direction] * i;

            if (board[currentRow][currentCol] != XMAS.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
