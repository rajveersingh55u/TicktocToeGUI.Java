

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TicTacToeGUI extends JFrame {

    private JButton[][] buttons = new JButton[3][3];
    private char[][] board = new char[3][3];

    private boolean playerTurn = true; // true = Human (X)
    private Random random = new Random();

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        
        setSize(500, 500);
        setForeground(Color.YELLOW);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        UIManager.put("Button.background", Color.LIGHT_GRAY);
        UIManager.put("Button.foreground", Color.blue);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        initBoard();
        initButtons();

        setVisible(true);
    }

    // Initialize board
    private void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Initialize buttons
    private void initButtons() {
        Font font = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton("");
                btn.setFont(font);
                final int row = i;
                final int col = j;

                btn.addActionListener(e -> playerMove(row, col));

                buttons[i][j] = btn;
                add(btn);
            }
        }
    }

    // Human move
    private void playerMove(int row, int col) {
        if (!playerTurn || board[row][col] != ' ') return;

        board[row][col] = 'X';
        buttons[row][col].setText("X");
        playerTurn = false;

        if (checkWin('X')) {
            showMessage("You Win!");
            resetGame();
            return;
        }

        if (checkDraw()) {
            showMessage("It's a Draw!");
            resetGame();
            return;
        }

        aiMove();
    }

    // AI move
    private void aiMove() {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != ' ');

        board[row][col] = 'O';
        buttons[row][col].setText("O");
        playerTurn = true;

        if (checkWin('O')) {
            showMessage("Computer Wins!");
            resetGame();
            return;
        }

        if (checkDraw()) {
            showMessage("It's a Draw!");
            resetGame();
        }
    }

    // Win check
    private boolean checkWin(char mark) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark)
                return true;
            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark)
                return true;
        }

        return (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) ||
               (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark);
    }

    // Draw check
    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    // Message dialog
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Reset game
    private void resetGame() {
        initBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        playerTurn = true;
    }

    // Main method
    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
