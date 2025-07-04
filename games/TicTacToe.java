package games;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.util.Scanner;

public class TicTacToe implements Game {
    private final char[][] board = new char[3][3];
    private boolean won = false;
    private boolean lost = false;
    private boolean tie = false;
    private int currentRound = 1;

    public TicTacToe() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    @Override
    public void playNextRound() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tic Tac Toe beginnt! Du bist X, der Gegner ist O.");

        for (int turn = 0; turn < 9; turn++) {
            printBoard();

            if (turn % 2 == 0) {
                System.out.print("Dein Zug (z.B. 1 1): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                if (board[row][col] != ' ') {
                    System.out.println("Ungültiger Zug, versuch es erneut.");
                    turn--;
                    continue;
                }
                board[row][col] = 'X';
            } else {
                System.out.println("Gegner zieht...");
                makeEnemyMove();
            }

            if (checkWin()) {
                printBoard();
                if (turn % 2 == 0) {
                    System.out.println("Du hast gewonnen!");
                    won = true;
                } else {
                    System.out.println("Du hast verloren!");
                    lost = true;
                }
                return;
            }

            currentRound++;
        }

        System.out.println("Unentschieden!");
        tie = true;
    }

    @Override
    public int getCurrentRound() {
        return currentRound;
    }

    @Override
    public boolean isFinished() {
        return won || lost || tie;
    }

    @Override
    public boolean isWon() {
        return won;
    }

    @Override
    public boolean isLost() {
        return lost;
    }

    @Override
    public boolean isTie() {
        return tie;
    }

    private void makeEnemyMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    return;
                }
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2]) return true;

            if (board[0][i] != ' ' &&
                    board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i]) return true;
        }

        if (board[0][0] != ' ' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) return true;

        if (board[0][2] != ' ' &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) return true;

        return false;
    }

    private void printBoard() {
        System.out.println("\nAktuelles Spielfeld:");
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("---+---+---");
        }
    }
}
