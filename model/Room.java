package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import challenges.Challenge;
import java.io.Serializable;

public class Room implements Serializable {

    private static final long serialVersionUID = 3947234501234725063L;

    private int number;
    private Challenge challenge;
    private Artifact artifact;
    private boolean open;

    public Room(int number, Challenge challenge, Artifact artifact) {
        this.number = number;
        this.challenge = challenge;
        this.artifact = artifact;
        this.open = false;
    }


    public int getNumber() {
        return number;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Artifact getArtifact() {
        return artifact;
    }


    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        if (!this.open && open) {
            this.open = true;
        }
    }
}
