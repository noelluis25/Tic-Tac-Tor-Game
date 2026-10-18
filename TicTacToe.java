public class TicTacToe extends Game {
    public static final int SIZE = 3;

    private Mark[][] board;
    private boolean[][] winningCells;
    private boolean gameOver;
    private Player winner;
    private int draws;

    public TicTacToe(Player player1, Player player2) {
        super(player1, player2);
        draws = 0;
        reset();
    }

    @Override
    public void reset() {
        board = new Mark[SIZE][SIZE];
        winningCells = new boolean[SIZE][SIZE];
        gameOver = false;
        winner = null;
        firstTurn();
    }

    @Override
    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false;
        }
        if (gameOver || board[row][col] != null) {
            return false;
        }

        Mark mark = getCurrentPlayer().getMark();
        board[row][col] = mark;

        if (checkWin(mark)) {
            winner = getCurrentPlayer();
            winner.addPoint();
            gameOver = true;
        } else if (boardIsFull()) {
            draws++;
            gameOver = true;
        } else {
            nextTurn();
        }
        return true;
    }

    private boolean checkWin(Mark mark) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) {
                for (int j = 0; j < SIZE; j++) {
                    winningCells[i][j] = true;
                }
                return true;
            }

            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark) {
                for (int j = 0; j < SIZE; j++) {
                    winningCells[j][i] = true;
                }
                return true;
            }
        }

        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) {
            for (int i = 0; i < SIZE; i++) {
                winningCells[i][i] = true;
            }
            return true;
        }

        if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark) {
            for (int i = 0; i < SIZE; i++) {
                winningCells[i][SIZE - 1 - i] = true;
            }
            return true;
        }
        return false;
    }

    private boolean boardIsFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Mark getCell(int row, int col) {
        return board[row][col];
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    public boolean isDraw() {
        return gameOver && winner == null;
    }

    @Override
    public boolean isWinningCell(int row, int col) {
        return winningCells[row][col];
    }

    @Override
    public int getDraws() {
        return draws;
    }

    @Override
    public void resetScores() {
        super.resetScores();
        draws = 0;
        reset();
    }

    public static String getRules() {
        return "1. Player X goes first.\n"
                + "2. Players take turns clicking an empty square.\n"
                + "3. Get three marks in a row, column or diagonal to win.\n"
                + "4. If all squares fill without a winner, it is a draw.";
    }
}
