package gui;

import database.UserDAO;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class GameFrame extends JFrame {

    JButton[][] board;

    JButton restartButton;
    JButton lobbyButton;

    JLabel turnLabel;

    String username;

    boolean aiMode = true;
    boolean xTurn = true;
    boolean gameOver = false;

    public GameFrame(String username) {
        this.username = username;

        setTitle("TicArena - Game");
        setSize(650, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // top panel

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(44, 62, 80));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("TICARENA");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        turnLabel = new JLabel("Current Turn : X");
        turnLabel.setFont(new Font("Arial", Font.BOLD, 18));
        turnLabel.setForeground(Color.WHITE);
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(titleLabel);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(turnLabel);
        topPanel.add(Box.createVerticalStrut(10));

        // board panel

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(5, 5, 5, 5));
        boardPanel.setBackground(new Color(44, 62, 80));

        board = new JButton[5][5];

        Font buttonFont = new Font("Arial", Font.BOLD, 30);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new JButton();
                board[i][j].setFont(buttonFont);
                board[i][j].setBackground(new Color(236, 240, 241));
                board[i][j].setForeground(new Color(44, 62, 80));
                board[i][j].setFocusPainted(false);
                board[i][j].setBorder(BorderFactory.createLineBorder(new Color(52, 73, 94), 2));

                int row = i;
                int col = j;

                board[i][j].addActionListener(
                        e -> {
                            if (gameOver)
                                return;
                            if (xTurn) {
                                board[row][col].setText("X");
                            } else {
                                board[row][col].setText("O");
                            }
                            board[row][col].setEnabled(false);

                            xTurn = !xTurn;

                            if (xTurn) {
                                turnLabel.setText("Current Turn : X");
                            } else {
                                turnLabel.setText("Current Turn : O");
                            }
                            checkWinner();

                            if (!gameOver && aiMode && !xTurn) {
                                makeAIMove();
                            }
                        });

                boardPanel.add(board[i][j]);
            }
        }

        // button panel

        JPanel buttonPanel = new JPanel();

        restartButton = new JButton("Restart Game");
        restartButton.setBackground(new Color(52, 152, 219));
        restartButton.setForeground(Color.WHITE);
        restartButton.setFocusPainted(false);
        restartButton.setFont(new Font("Arial", Font.BOLD, 16));
        restartButton.addActionListener(
                e -> {
                    dispose();
                    new GameFrame(username);
                });

        lobbyButton = new JButton("Back To Lobby");
        lobbyButton.setBackground(new Color(46, 204, 113));
        lobbyButton.setForeground(Color.WHITE);
        lobbyButton.setFocusPainted(false);
        lobbyButton.setFont(new Font("Arial", Font.BOLD, 16));
        lobbyButton.addActionListener(
                e -> {
                    new LobbyFrame(username);
                    dispose();
                });

        buttonPanel.add(restartButton);
        buttonPanel.add(lobbyButton);

        // main Layout

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }

    public void checkWinner() {

        // Rows

        for (int i = 0; i < 5; i++) {
            String first = board[i][0].getText();

            if (first.equals(""))
                continue;

            boolean same = true;

            for (int j = 1; j < 5; j++) {
                if (!board[i][j].getText().equals(first)) {
                    same = false;
                    break;
                }
            }

            if (same) {
                gameOver = true;
                new UserDAO().addWin(username);
                JOptionPane.showMessageDialog(this, first + " Wins!");
                disableBoard();
                return;
            }
        }

        // Columns

        for (int j = 0; j < 5; j++) {
            String first = board[0][j].getText();

            if (first.equals(""))
                continue;

            boolean same = true;

            for (int i = 1; i < 5; i++) {
                if (!board[i][j].getText().equals(first)) {
                    same = false;
                    break;
                }
            }

            if (same) {
                gameOver = true;
                new UserDAO().addWin(username);
                JOptionPane.showMessageDialog(this, first + " Wins!");
                disableBoard();
                return;
            }
        }

        // Main Diagonal

        String first = board[0][0].getText();

        if (!first.equals("")) {

            boolean same = true;

            for (int i = 1; i < 5; i++) {
                if (!board[i][i].getText().equals(first)) {
                    same = false;
                    break;
                }
            }

            if (same) {
                gameOver = true;
                new UserDAO().addWin(username);
                JOptionPane.showMessageDialog(this, first + " Wins!");
                disableBoard();
                return;
            }
        }

        // Opposite Diagonal

        first = board[0][4].getText();

        if (!first.equals("")) {
            boolean same = true;

            for (int i = 1; i < 5; i++) {
                if (!board[i][4 - i].getText().equals(first)) {
                    same = false;
                    break;
                }
            }

            if (same) {
                gameOver = true;
                new UserDAO().addWin(username);
                JOptionPane.showMessageDialog(this, first + " Wins!");
                disableBoard();
                return;
            }
        }

        // Draw

        boolean boardFull = true;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].getText().equals("")) {
                    boardFull = false;
                    break;
                }
            }
        }

        if (boardFull) {
            gameOver = true;
            new UserDAO().addDraw(username);
            JOptionPane.showMessageDialog(this, "Game Over! It's a Draw.");

            disableBoard();
        }
    }

    public void makeAIMove() {

        //Step 1: Can AI win?

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].getText().equals("")) {
                    if (canWin(i, j, "O")) {
                        board[i][j].setText("O");
                        board[i][j].setEnabled(false);
                        xTurn = true;

                        turnLabel.setText("Current Turn : X");

                        checkWinner();
                        return;
                    }
                }
            }
        }
        
        // Step 2: Block player's winning move

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].getText().equals("")) {
                    if (canWin(i, j, "X")) {
                        board[i][j].setText("O");
                        board[i][j].setEnabled(false);

                        xTurn = true;

                        turnLabel.setText("Current Turn : X");

                        checkWinner();
                        return;
                    }
                }
            }
        }

        // Step 3: Random move

        ArrayList<JButton> emptyCells = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].getText().equals("")) {
                    emptyCells.add(board[i][j]);
                }
            }
        }

        if (emptyCells.isEmpty())
            return;

        Random random = new Random();

        JButton move = emptyCells.get(random.nextInt(emptyCells.size()));

        move.setText("O");

        move.setEnabled(false);

        xTurn = true;

        turnLabel.setText("Current Turn : X");

        checkWinner();
    }

    public boolean canWin(int row, int col, String symbol) {

        board[row][col].setText(symbol);

        // check row

        boolean win = true;

        for (int j = 0; j < 5; j++) {

            if (!board[row][j].getText().equals(symbol)) {
                win = false;
                break;
            }
        }

        if (win) {
            board[row][col].setText("");
            return true;
        }

        // check column

        win = true;

        for (int i = 0; i < 5; i++) {
            if (!board[i][col].getText().equals(symbol)) {
                win = false;
                break;
            }
        }

        if (win) {
            board[row][col].setText("");
            return true;
        }

        board[row][col].setText("");

        return false;
    }

    public void disableBoard() {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j].setEnabled(false);
            }
        }
    }
}