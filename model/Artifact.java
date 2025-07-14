package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.io.Serializable;

/**
 * Die Klasse {@code Artifact} repräsentiert ein spezielles Bauteil (Artefakt),
 * das vom Roboter während des Spiels gefunden und später im Shuttle installiert werden kann.
 */
public class Artifact implements Serializable {

    private static final long serialVersionUID = 3743681153808233500L;

    /**
     * Konstanten für die drei Arten von Artefakten.
     */
    public static final String NAVIGATION_MODULE = "🧭 Navigationsmodul";
    public static final String CONTROL_SYSTEM = "🎮 Steuerungssystem";
    public static final String ENERGY_CRYSTAL = "💎 Energiekristall";

    /**
     * Name des Artefakts.
     */
    private final String name;

    /**
     * Gibt an, ob das Artefakt gefunden wurde.
     */
    private boolean found;

    /**
     * Gibt an, ob das Artefakt im Shuttle installiert wurde.
     */
    private boolean installed;

    /**
     * Konstruktor erstellt ein neues Artefakt mit gegebenem Namen.
     * Standardmäßig ist es weder gefunden noch installiert.
     *
     * @param name Der Name des Artefakts
     */
    public Artifact(String name) {
        this.name = name;
        this.found = false;
        this.installed = false;
    }

    /**
     * Gibt den Namen des Artefakts zurück.
     *
     * @return Name des Artefakts
     */
    public String getName() {
        return name;
    }

    /**
     * Prüft, ob das Artefakt gefunden wurde.
     *
     * @return {@code true}, wenn gefunden; sonst {@code false}
     */
    public boolean isFound() {
        return found;
    }

    /**
     * Markiert das Artefakt als gefunden.
     *
     * @param found {@code true}, wenn gefunden
     */
    public void setFound(boolean found) {
        this.found = found;
    }

    /**
     * Prüft, ob das Artefakt installiert wurde.
     *
     * @return {@code true}, wenn installiert; sonst {@code false}
     */
    public boolean isInstalled() {
        return installed;
    }

    /**
     * Installiert das Artefakt – nur möglich, wenn es zuvor gefunden wurde.
     */
    public void install() {
        if (found) {
            this.installed = true;
        }
    }
}
