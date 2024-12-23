package days;

import java.util.List;

public class Day4 {

    private static final String XMAS = "XMAS";
    private static final String MAS = "MAS";
    private static final String SAM = "SAM";
    private static final int[] DX = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] DY = {-1, 0, 1, -1, 1, -1, 0, 1};


    public static void partOne() {
        List<String> list = Helper.readLines("day4.txt");
        int result = countXMAS(list);
        System.out.println(result);
    }

    public static void partTwo() {
        List<String> list = Helper.readLines("day4.txt");
        int result = countMAS_X(list);
        System.out.println(result);
    }

    private static int countMAS_X(List<String> list) {
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
                if (board[i][j] == MAS.charAt(1)) {
                    if (checkDiagonal(board, i, j) && checkBackwardsDiagonal(board, i, j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean checkDiagonal(char[][] board, int startRow, int startCol) {

        boolean mas = true;
        if (startRow - 1 + MAS.length() > board.length || startRow - 1 < 0 || startCol - 1 < 0 || startCol - 1 + MAS.length() > board[0].length) {
            return false;
        }
        int row = startRow - 1;
        int col = startCol - 1;
        for (int i = 0; i < MAS.length(); i++) {
            int currentRow = row + i;
            int currentCol = col + i;
            if (board[currentRow][currentCol] != MAS.charAt(i)) {
                mas = false;
                break;
            }
        }
        boolean sam = true;
        for (int i = 0; i < SAM.length(); i++) {
            int currentRow = row + i;
            int currentCol = col + i;
            if (board[currentRow][currentCol] != SAM.charAt(i)) {
                sam = false;
                break;
            }
        }
        return mas || sam;
    }

    private static boolean checkBackwardsDiagonal(char[][] board, int startRow, int startCol) {

        boolean mas = true;
        if (startRow - 1 + MAS.length() > board.length || startRow - 1 < 0 || startCol + 1 - MAS.length() < -1) {
            return false;
        }
        int row = startRow - 1;
        int col = startCol + 1;
        for (int i = 0; i < MAS.length(); i++) {
            int currentRow = row + i;
            int currentCol = col - i;
            if (board[currentRow][currentCol] != MAS.charAt(i)) {
                mas = false;
                break;
            }
        }
        boolean sam = true;
        for (int i = 0; i < SAM.length(); i++) {
            int currentRow = row + i;
            int currentCol = col - i;
            if (board[currentRow][currentCol] != SAM.charAt(i)) {
                sam = false;
                break;
            }
        }
        return sam || mas;
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
