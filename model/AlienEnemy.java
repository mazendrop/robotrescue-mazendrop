package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

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
     * Kampfverhalten des Aliens: entzieht dem Roboter 15 Energiepunkte.
     *
     * @param robot Der Roboter, der angegriffen wird
     */
    @Override
    public void fight(Robot robot) {
        int energyDrain = 15;
        System.out.println(name + " entzieht dem Roboter Energie!");
        robot.drainEnergy(energyDrain);
    }
}
