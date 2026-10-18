import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private Game game;
    private JButton[][] buttons;
    private JLabel turnLabel;
    private JLabel scoreLabel;

    public GameWindow() {
        Player playerX = new Player("Player X", Mark.X);
        Player playerO = new Player(Mark.O);
        game = new TicTacToe(playerX, playerO);

        setTitle("Tic Tac Toe - Java OOP Project");
        setSize(470, 590);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBackground(new Color(240, 244, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        setContentPane(mainPanel);

        JLabel title = new JLabel("TIC TAC TOE", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 29));
        title.setForeground(new Color(36, 56, 83));

        turnLabel = new JLabel("", SwingConstants.CENTER);
        turnLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
        turnLabel.setForeground(new Color(70, 83, 103));

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 0, 6));
        topPanel.setOpaque(false);
        topPanel.add(title);
        topPanel.add(turnLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 8, 8));
        boardPanel.setBackground(new Color(220, 229, 242));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        buttons = new JButton[TicTacToe.SIZE][TicTacToe.SIZE];

        for (int row = 0; row < TicTacToe.SIZE; row++) {
            for (int col = 0; col < TicTacToe.SIZE; col++) {
                JButton button = new JButton("");
                button.setFont(new Font("SansSerif", Font.BOLD, 58));
                button.setBackground(Color.WHITE);
                button.setFocusPainted(false);
                button.setBorder(BorderFactory.createLineBorder(new Color(208, 220, 234), 1));

                final int r = row;
                final int c = col;
                button.addActionListener(e -> playMove(r, c));
                buttons[row][col] = button;
                boardPanel.add(button);
            }
        }
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        scoreLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 15));

        JButton newRoundButton = new JButton("New Round");
        newRoundButton.addActionListener(e -> {
            game.reset();
            updateScreen();
        });

        JButton resetScoresButton = new JButton("Reset Scores");
        resetScoresButton.addActionListener(e -> {
            game.resetScores();
            updateScreen();
        });


        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 8, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(newRoundButton);
        buttonPanel.add(resetScoresButton);


        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 0, 12));
        bottomPanel.setOpaque(false);
        bottomPanel.add(scoreLabel);
        bottomPanel.add(buttonPanel);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        updateScreen();
    }

    private void playMove(int row, int col) {
        if (game.makeMove(row, col)) {
            updateScreen();
        }
    }

    private void updateScreen() {
        for (int row = 0; row < TicTacToe.SIZE; row++) {
            for (int col = 0; col < TicTacToe.SIZE; col++) {
                Mark mark = game.getCell(row, col);
                JButton button = buttons[row][col];

                if (mark == null) {
                    button.setText("");
                    button.setBackground(Color.WHITE);
                } else {
                    button.setText(mark.toString());
                    if (mark == Mark.X) {
                        button.setForeground(new Color(42, 104, 196));
                    } else {
                        button.setForeground(new Color(219, 106, 51));
                    }
                    button.setBackground(Color.WHITE);
                }

                if (game.isWinningCell(row, col)) {
                    button.setBackground(new Color(190, 235, 198));
                }
            }
        }

        if (game.getWinner() != null) {
            turnLabel.setText(game.getWinner().getName() + " wins!");
        } else if (game.isDraw()) {
            turnLabel.setText("It's a draw!");
        } else {
            turnLabel.setText("Turn: " + game.getCurrentPlayer().getName());
        }

        scoreLabel.setText("X: " + game.getPlayer1().getScore()
                + "     O: " + game.getPlayer2().getScore()
                + "     Draws: " + game.getDraws());
    }
}
