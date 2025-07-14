package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.util.Scanner;

/**
 * Die Klasse {@code DroneEnemy} ist ein konkreter Gegnertyp, der vom Roboter bekämpft werden kann.
 * Beim Kampf verursacht die Drohne Schaden am Roboter.
 */
public class DroneEnemy extends Enemy {

    /**
     * Konstruktor für eine neue Drohne.
     *
     * @param name        Name des Gegners (z.B. „Drohne“)
     * @param lifePoints  Lebenspunkte der Drohne (wird ggf. nicht verwendet)
     */
    public DroneEnemy(String name, int lifePoints) {
        super(name, lifePoints);
    }

    /**
     * Implementiert das Kampfverhalten der Drohne.
     * Der Roboter muss eine einfache Rechenfrage lösen. Bei falscher Antwort
     * verursacht die Drohne 10 Schadenspunkte.
     *
     * @param robot Der betroffene Roboter
     */

    @Override
    public void fight(Robot robot) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + " fordert dich zu einem Duell heraus!");
        System.out.print("Was ist 3 + 4? ");
        String antwort = scanner.nextLine();

        if (antwort.trim().equals("7")) {
            System.out.println("Richtige Antwort! Du hast den Kampf gewonnen!");
            robot.addExperiencePoints(10);
        } else {
            System.out.println("Falsche Antwort! Du hast Schaden erlitten.");
            robot.takeDamage(10);
        }
    }

}
