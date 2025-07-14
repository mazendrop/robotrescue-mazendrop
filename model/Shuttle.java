package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.io.Serializable;

/**
 * Die Klasse {@code Shuttle} repräsentiert ein Raumschiff, das drei Artefakte benötigt,
 * um startbereit zu sein. Diese Artefakte sind:
 * <ul>
 *     <li>Navigationsmodul</li>
 *     <li>Steuerungssystem</li>
 *     <li>Energiekristall</li>
 * </ul>
 *
 * Die Klasse implementiert {@code Serializable}, um den Zustand zu speichern.
 */
public class Shuttle implements Serializable {

    private static final long serialVersionUID = 3230694418L;

    // Name des Shuttles
    private final String name;

    // Liste der benötigten Artefakte (immer genau drei)
    private final Artifact[] artifacts = new Artifact[3];

    /**
     * Konstruktor: Erstellt ein Shuttle mit gegebenem Namen und initialisiert
     * die drei benötigten Artefakte (Navigation, Steuerung, Energie).
     *
     * @param name Name des Shuttles
     */
    public Shuttle(String name) {
        this.name = name;

        artifacts[0] = new Artifact(Artifact.NAVIGATION_MODULE);
        artifacts[1] = new Artifact(Artifact.CONTROL_SYSTEM);
        artifacts[2] = new Artifact(Artifact.ENERGY_CRYSTAL);
    }

    /**
     * Gibt den Namen des Shuttles zurück.
     *
     * @return Name als String
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt das Array aller Artefakte des Shuttles zurück.
     *
     * @return Array von {@code Artifact}
     */
    public Artifact[] getArtifacts() {
        return artifacts;
    }

    /**
     * Sucht ein Artefakt im Shuttle anhand seines Namens.
     *
     * @param artifactName Name des gesuchten Artefakts
     * @return Das Artefakt mit entsprechendem Namen oder {@code null}, wenn nicht gefunden
     */
    public Artifact getArtifactByName(String artifactName) {
        for (Artifact artifact : artifacts) {
            if (artifact.getName().equalsIgnoreCase(artifactName)) {
                return artifact;
            }
        }
        return null;
    }

    /**
     * Prüft, ob alle drei Artefakte installiert sind – nur dann ist das Shuttle startbereit.
     *
     * @return {@code true}, wenn alle Artefakte installiert; sonst {@code false}
     */
    public boolean isReadyToLaunch() {
        for (Artifact artifact : artifacts) {
            if (!artifact.isInstalled()) {
                return false;
            }
        }
        return true;
    }
}
