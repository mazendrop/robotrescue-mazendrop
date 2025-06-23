package model;

import java.io.Serializable;
import java.util.Random;

public class Robot implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = -5081867320134061285L;

    private String name;
    private int energy;
    private int damage;
    private int experiencePoints;
    private boolean operational;

    public Robot(String name) {
        this.name = name;
        this.energy = 50;
        this.damage = 0;
        this.experiencePoints = 0;
        this.operational = true;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public void recharge(int amount) {
        if (!operational) return;
        energy += amount;
        if (energy > 100) {
            energy = 100;
        }
    }

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

    public int getDamage() {
        return damage;
    }

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

    public void repairDamage(int amount) {
        damage -= amount;
        if (damage < 0) {
            damage = 0;
        }
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void addExperiencePoints(int amount) {
        experiencePoints += amount;
    }

    public boolean isOperational() {
        return operational;
    }
}

