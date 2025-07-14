package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import challenges.Challenge;
import java.io.Serializable;

/**
 * Die Klasse {@code Room} repräsentiert einen Raum im Spiel, der eine Herausforderung
 * ({@code Challenge}) und ein Artefakt enthalten kann. Jeder Raum hat eine Nummer
 * und kann geöffnet oder geschlossen sein.
 *
 * Die Klasse ist {@code Serializable}, damit Spielstände gespeichert und geladen werden können.
 */
public class Room implements Serializable {

    private static final long serialVersionUID = 3947234501234725063L;

    // Raum-Nummer (1, 2, 3, ...)
    private int number;

    // Die Challenge (z. B. Morsecode, SecretCode usw.), die gelöst werden muss
    private Challenge challenge;

    // Das Artefakt, das sich im Raum befindet (falls vorhanden)
    private Artifact artifact;

    // Status, ob der Raum bereits geöffnet wurde
    private boolean open;

    /**
     * Konstruktor für einen Raum mit gegebener Nummer, Herausforderung und Artefakt.
     * Beim Erstellen ist der Raum zunächst geschlossen.
     *
     * @param number    Nummer des Raumes
     * @param challenge Die Challenge in diesem Raum
     * @param artifact  Das Artefakt in diesem Raum
     */
    public Room(int number, Challenge challenge, Artifact artifact) {
        this.number = number;
        this.challenge = challenge;
        this.artifact = artifact;
        this.open = false;
    }

    /**
     * Gibt die Raum-Nummer zurück.
     *
     * @return Raum-Nummer
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gibt die Challenge in diesem Raum zurück.
     *
     * @return Die Challenge
     */
    public Challenge getChallenge() {
        return challenge;
    }

    /**
     * Gibt das Artefakt in diesem Raum zurück.
     *
     * @return Das Artefakt
     */
    public Artifact getArtifact() {
        return artifact;
    }

    /**
     * Gibt zurück, ob der Raum bereits geöffnet wurde.
     *
     * @return {@code true}, wenn geöffnet; sonst {@code false}
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Setzt den Raum auf „geöffnet“, wenn er zuvor noch geschlossen war.
     *
     * @param open {@code true}, wenn der Raum geöffnet werden soll
     */
    public void setOpen(boolean open) {
        if (!this.open && open) {
            this.open = true;
        }
    }
}
