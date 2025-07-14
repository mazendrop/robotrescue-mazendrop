package games;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.util.Scanner;

/**
 * Die Klasse {@code Nim} implementiert das Spiel "Nim", bei dem Spieler und Gegner
 * abwechselnd 1–3 Stäbchen nehmen. Wer das letzte Stäbchen nimmt, verliert.
 */
public class Nim implements Game {

    /** Anfangsanzahl der Stäbchen im Spiel */
    private int sticks = 12;

    /** Aktuelle Rundenzahl */
    private int currentRound = 1;

    /** Spielstatus: ob das Spiel zu Ende ist */
    private boolean finished = false;

    /** Ergebnis: Spiel gewonnen */
    private boolean won = false;

    /** Ergebnis: Spiel verloren */
    private boolean lost = false;

    /** Ergebnis: Unentschieden (wird hier nicht verwendet) */
    private boolean tie = false;

    /**
     * Führt die nächste Runde aus.
     * Zuerst spielt der Nutzer, danach der Gegner.
     * Wer das letzte Stäbchen nimmt, verliert.
     */
    @Override
    public void playNextRound() {
        if (finished) return;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Es gibt noch " + sticks + " Stäbchen.");
        System.out.print("Wie viele Stäbchen möchtest du nehmen? (1–3): ");
        int choice = scanner.nextInt();

        // Eingabeprüfung
        if (choice < 1 || choice > 3 || choice > sticks) {
            System.out.println("Ungültige Eingabe.");
            return;
        }

        // Spieler nimmt Stäbchen
        sticks -= choice;

        // Spieler verliert, wenn er das letzte Stäbchen nimmt
        if (sticks == 0) {
            System.out.println("Du hast das letzte Stäbchen genommen!");
            lost = true;
            finished = true;
            return;
        }

        // Gegner nimmt Stäbchen (immer möglichst viele bis max. 3)
        int enemyChoice = Math.min(3, sticks);
        System.out.println("Der Gegner nimmt " + enemyChoice + " Stäbchen.");
        sticks -= enemyChoice;

        // Spieler gewinnt, wenn der Gegner das letzte Stäbchen nimmt
        if (sticks == 0) {
            System.out.println("Der Gegner hat das letzte Stäbchen genommen!");
            won = true;
            finished = true;
        }

        currentRound++;
    }

    /** Gibt die aktuelle Rundenzahl zurück */
    @Override
    public int getCurrentRound() {
        return currentRound;
    }

    /** Gibt zurück, ob das Spiel beendet wurde */
    @Override
    public boolean isFinished() {
        return finished;
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

    /** Gibt zurück, ob das Spiel unentschieden endete (hier immer false) */
    @Override
    public boolean isTie() {
        return tie;
    }
}
