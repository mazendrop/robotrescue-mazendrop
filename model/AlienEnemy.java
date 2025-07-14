package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.util.Scanner;

/**
 * Die Klasse {@code AlienEnemy} stellt einen feindlichen Gegner vom Typ "Alien" dar,
 * der im Kampf dem Roboter Energie entzieht.
 * Sie erweitert die abstrakte Basisklasse {@link Enemy}.
 */
public class AlienEnemy extends Enemy {

    /**
     * Konstruktor initialisiert den Namen und die Lebenspunkte des Aliens.
     *
     * @param name       Name des Gegners
     * @param lifePoints Lebenspunkte des Gegners
     */
    public AlienEnemy(String name, int lifePoints) {
        super(name, lifePoints);
    }

    /**
     * Kampfverhalten des Aliens:
     * Der Roboter muss eine einfache Rechenfrage beantworten. Bei falscher Antwort
     * entzieht das Alien dem Roboter 15 Energiepunkte.
     *
     * @param robot Der Roboter, der angegriffen wird
     */


    @Override
    public void fight(Robot robot) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + " fordert dich zu einem Duell heraus!");
        System.out.print("Was ist 17 * 9? ");
        String antwort = scanner.nextLine();

        if (antwort.trim().equals("153")) {
            System.out.println("Richtige Antwort! Du hast den Kampf gewonnen!");
            robot.addExperiencePoints(15);
        } else {
            System.out.println("Falsche Antwort! Du hast Schaden erlitten.");
            robot.takeDamage(15);
        }
    }

}
