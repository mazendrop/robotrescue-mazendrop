package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

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
     * Die Drohne verursacht 10 Schadenspunkte am Roboter.
     *
     * @param robot Der betroffene Roboter
     */
    @Override
    public void fight(Robot robot) {
        int damage = 10;
        System.out.println(name + " greift den Roboter an!");
        robot.takeDamage(damage);
    }
}
