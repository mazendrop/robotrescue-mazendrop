package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.io.Serializable;

public class Shuttle implements Serializable {

    private static final long serialVersionUID = 3230694418L;

    private final String name;
    private final Artifact[] artifacts = new Artifact[3];

    public Shuttle(String name) {
        this.name = name;

        artifacts[0] = new Artifact(Artifact.NAVIGATION_MODULE);
        artifacts[1] = new Artifact(Artifact.CONTROL_SYSTEM);
        artifacts[2] = new Artifact(Artifact.ENERGY_CRYSTAL);
    }

    public String getName() {
        return name;
    }

    public Artifact[] getArtifacts() {
        return artifacts;
    }

    public Artifact getArtifactByName(String artifactName) {
        for (Artifact artifact : artifacts) {
            if (artifact.getName().equalsIgnoreCase(artifactName)) {
                return artifact;
            }
        }
        return null;
    }

    public boolean isReadyToLaunch() {
        for (Artifact artifact : artifacts) {
            if (!artifact.isInstalled()) {
                return false;
            }
        }
        return true;
    }
}
