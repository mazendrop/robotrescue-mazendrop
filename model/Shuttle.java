package model;

import java.io.Serializable;

public class Shuttle implements Serializable {

    private static final long serialVersionUID = 3230694418L;

    private final String name;

    private final Artifact[] artifacts = new Artifact[] {
            new Artifact(Artifact.NAVIGATION_MODULE),
            new Artifact(Artifact.CONTROL_SYSTEM),
            new Artifact(Artifact.ENERGY_CRYSTAL)
    };

    public Shuttle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Artifact[] getArtifacts() {
        return artifacts;
    }

    /**
     * Gibt ein Artefakt anhand des übergebenen Namens zurück.
     * Die Suche soll unabhängig von Groß- und Kleinschreibung erfolgen.
     * Wenn kein Artefakt mit dem Namen gefunden wird, soll null zurückgegeben werden.
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
     * Prüft, ob alle Artefakte installiert sind.
     * Das Shuttle ist nur startbereit, wenn jedes Artefakt installiert wurde.
     * Gibt true zurück, wenn alle installiert sind, sonst false.
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