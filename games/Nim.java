package games;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.util.Scanner;

public class Nim implements Game {

    private int sticks = 12;
    private int currentRound = 1;
    private boolean finished = false;
    private boolean won = false;
    private boolean lost = false;
    private boolean tie = false;

    @Override
    public void playNextRound() {
        if (finished) return;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Es gibt noch " + sticks + " Stäbchen.");
        System.out.print("Wie viele Stäbchen möchtest du nehmen? (1–3): ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > 3 || choice > sticks) {
            System.out.println("Ungültige Eingabe.");
            return;
        }

        sticks -= choice;

        if (sticks == 0) {
            System.out.println("Du hast das letzte Stäbchen genommen!");
            lost = true;
            finished = true;
            return;
        }

        // Gegnerzug
        int enemyChoice = Math.min(3, sticks);
        System.out.println("Der Gegner nimmt " + enemyChoice + " Stäbchen.");
        sticks -= enemyChoice;

        if (sticks == 0) {
            System.out.println("Der Gegner hat das letzte Stäbchen genommen!");
            won = true;
            finished = true;
        }

        currentRound++;
    }

    @Override
    public int getCurrentRound() {
        return currentRound;
    }

    @Override
    public boolean isFinished() {
        return finished;
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
}
