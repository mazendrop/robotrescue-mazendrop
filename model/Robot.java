package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.io.Serializable;

/**
 * Die Klasse {@code Robot} repräsentiert den spielbaren Roboter.
 * Der Roboter besitzt Energie, Schaden, Erfahrungswerte und kann im Spielzustand „betriebsbereit“ oder „ausgefallen“ sein.
 * Die Klasse ist {@code Serializable}, damit Spielstände gespeichert und wieder geladen werden können.
 */
public class Robot implements Serializable {

    private static final long serialVersionUID = -5081867320134061285L;

    private String name;
    private int energy;
    private int damage;
    private int experiencePoints;
    private boolean operational;

    // Maximale Energie des Roboters
    private final int maxEnergy = 100;

    /**
     * Konstruktor: Initialisiert einen Roboter mit Name, Standardwerten für Energie und Zustand.
     *
     * @param name Name des Roboters
     */
    public Robot(String name) {
        this.name = name;
        this.energy = 50;
        this.damage = 0;
        this.experiencePoints = 0;
        this.operational = true;
    }

    // Getter-Methoden

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public int getDamage() {
        return damage;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public boolean isOperational() {
        return operational;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    // Energie-Management

    /**
     * Lädt Energie des Roboters auf.
     * Energie wird nur geladen, wenn der Roboter betriebsbereit ist.
     *
     * @param amount Menge an Energie, die aufgeladen wird
     */
    public void recharge(int amount) {
        if (!operational) return;
        energy += amount;
        if (energy > 100) {
            energy = 100;
        }
    }

    /**
     * Zieht dem Roboter Energie ab, mit zufälligen Modifikationen (kritisch oder schwach).
     * Wenn Energie ≤ 0, ist der Roboter nicht mehr betriebsbereit.
     *
     * @param amount Menge an Energieverlust
     */
    public void drainEnergy(int amount) {
        if (!operational) return;

        int effective = amount;
        double random = Math.random();

        if (random < 0.1) {
            effective *= 2;
            System.out.println("Kritischer Energieverlust!");
        } else if (random < 0.2) {
            effective /= 2;
            System.out.println("Schwacher Energieverlust!");
        }

        energy -= effective;
        if (energy <= 0) {
            energy = 0;
            operational = false;
            System.out.println("Der Roboter hat keine Energie mehr!");
        }
    }

    // Schadens-Management

    /**
     * Fügt dem Roboter Schaden zu, ggf. mit zufälliger Modifikation.
     * Wenn Schaden ≥ 100, ist der Roboter vollständig beschädigt.
     *
     * @param amount Schaden in Punkten
     */
    public void takeDamage(int amount) {
        if (!operational) return;

        int effective = amount;
        double random = Math.random();

        if (random < 0.1) {
            effective *= 2;
            System.out.println("Kritischer Schaden!");
        } else if (random < 0.2) {
            effective /= 2;
            System.out.println("Schwacher Schaden!");
        }

        damage += effective;
        if (damage >= 100) {
            damage = 100;
            operational = false;
            System.out.println("Der Roboter ist vollständig beschädigt!");
        }
    }

    /**
     * Repariert einen Teil des erlittenen Schadens.
     *
     * @param amount Zu reparierender Schadenswert
     */
    public void repairDamage(int amount) {
        damage -= amount;
        if (damage < 0) {
            damage = 0;
        }
    }

    /**
     * Fügt dem Roboter Erfahrungspunkte hinzu.
     *
     * @param amount Anzahl der Punkte
     */
    public void addExperiencePoints(int amount) {
        experiencePoints += amount;
    }
}
