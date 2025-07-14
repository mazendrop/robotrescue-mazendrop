package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

/**
 * Die abstrakte Klasse {@code Enemy} definiert einen generischen Gegner,
 * der einen Namen und Lebenspunkte besitzt.
 * Spezifische Gegnertypen wie {@code DroneEnemy} oder {@code AlienEnemy} erben von dieser Klasse.
 */
public abstract class Enemy {

    /**
     * Name des Gegners ("Drohne" oder "Alien")
     */
    protected String name;

    /**
     * Aktuelle Lebenspunkte des Gegners
     */
    protected int lifePoints;

    /**
     * Konstruktor zur Initialisierung des Gegners mit Namen und Lebenspunkten.
     *
     * @param name        Name des Gegners
     * @param lifePoints  Lebenspunkte beim Start
     */
    public Enemy(String name, int lifePoints) {
        this.name = name;
        this.lifePoints = lifePoints;
    }

    /**
     * Reduziert die Lebenspunkte des Gegners um den angegebenen Wert.
     * Wenn die Lebenspunkte unter 0 fallen, werden sie auf 0 gesetzt.
     *
     * @param amount Schaden, der dem Gegner zugefügt wird
     */
    public void takeDamage(int amount) {
        lifePoints -= amount;
        if (lifePoints < 0) lifePoints = 0;
        System.out.println(name + " hat " + amount + " Schaden erlitten.");
        System.out.println(name + " hat noch " + lifePoints + " Lebenspunkte.");
    }

    /**
     * Prüft, ob der Gegner besiegt wurde (d.h. keine Lebenspunkte mehr hat).
     *
     * @return {@code true}, wenn der Gegner 0 oder weniger Lebenspunkte hat
     */
    public boolean isDefeated() {
        return lifePoints <= 0;
    }

    /**
     * Abstrakte Methode: Definiert das Kampfverhalten des Gegners.
     * Muss in den Unterklassen implementiert werden.
     *
     * @param robot Der betroffene Roboter
     */
    public abstract void fight(Robot robot);
}
