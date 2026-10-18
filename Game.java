// Abstract class for a two-player game
public abstract class Game implements Playable {
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    protected void nextTurn() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    protected void firstTurn() {
        currentPlayer = player1;
    }

    public void resetScores() {
        player1.resetScore();
        player2.resetScore();
    }

    public abstract Mark getCell(int row, int col);
    public abstract boolean isGameOver();
    public abstract Player getWinner();
    public abstract boolean isDraw();
    public abstract boolean isWinningCell(int row, int col);
    public abstract int getDraws();
}
