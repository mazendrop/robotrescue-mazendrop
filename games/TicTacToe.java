package games;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.util.Scanner;

/**
 * Die Klasse {@code TicTacToe} implementiert das klassische Spiel "Tic Tac Toe"
 * zwischen dem Spieler (X) und dem Computer (O) auf einem 3x3-Spielfeld.
 */
public class TicTacToe implements Game {

    /** Spielfeld mit 3x3 Feldern */
    private final char[][] board = new char[3][3];

    /** Status: Spieler hat gewonnen */
    private boolean won = false;

    /** Status: Spieler hat verloren */
    private boolean lost = false;

    /** Status: Unentschieden */
    private boolean tie = false;

    /** Aktuelle Rundenzahl */
    private int currentRound = 1;

    /**
     * Konstruktor initialisiert das Spielfeld mit Leerzeichen.
     */
    public TicTacToe() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Spielt die nächste Runde. Spieler und Gegner machen abwechselnd einen Zug.
     * Prüft nach jedem Zug auf einen Sieg oder ein Unentschieden.
     */
    @Override
    public void playNextRound() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tic Tac Toe beginnt! Du bist X, der Gegner ist O.");

        for (int turn = 0; turn < 9; turn++) {
            printBoard();

            // Spielerzug bei gerader Rundenzahl
            if (turn % 2 == 0) {
                System.out.print("Dein Zug (z.B. 1 1): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                // Prüft, ob Feld frei ist
                if (board[row][col] != ' ') {
                    System.out.println("Ungültiger Zug, versuch es erneut.");
                    turn--;  // Runde wird nicht gezählt
                    continue;
                }

                board[row][col] = 'X';
            } else {
                // Gegnerzug bei ungerader Rundenzahl
                System.out.println("Gegner zieht...");
                makeEnemyMove();
            }

            // Prüft, ob jemand gewonnen hat
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

        // Wenn 9 Züge gemacht wurden und keiner gewonnen hat → Unentschieden
        System.out.println("Unentschieden!");
        tie = true;
    }

    /** Gibt die aktuelle Rundenzahl zurück */
    @Override
    public int getCurrentRound() {
        return currentRound;
    }

    /** Gibt zurück, ob das Spiel vorbei ist */
    @Override
    public boolean isFinished() {
        return won || lost || tie;
    }

    /** Gibt zurück, ob das Spiel gewonnen wurde */
    @Override
    public boolean isWon() {
        return won;
    }

    /** Gibt zurück, ob das Spiel verloren wurde */
    @Override
    public boolean isLost() {
        return lost;
    }

    /** Gibt zurück, ob das Spiel unentschieden endete */
    @Override
    public boolean isTie() {
        return tie;
    }

    /**
     * Gegner macht einen Zug: wählt das erste freie Feld von oben links.
     */
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

    /**
     * Prüft, ob eine Gewinnkombination auf dem Spielfeld vorhanden ist.
     *
     * @return {@code true}, wenn ein Spieler gewonnen hat
     */
    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            // Horizontale Überprüfung
            if (board[i][0] != ' ' &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2]) return true;

            // Vertikale Überprüfung
            if (board[0][i] != ' ' &&
                    board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i]) return true;
        }

        // Diagonale Überprüfung
        if (board[0][0] != ' ' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) return true;

        if (board[0][2] != ' ' &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) return true;

        return false;
    }

    /**
     * Gibt das aktuelle Spielfeld im Terminal aus.
     */
    private void printBoard() {
        System.out.println("\nAktuelles Spielfeld:");
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("---+---+---");
        }
    }
}
