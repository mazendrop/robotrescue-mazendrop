package app;

import model.Robot;
import model.Room;
import model.Shuttle;
import model.Artifact;

public class RobotGame {
    private final Robot robot;
    private final Room[] rooms = new Room[3];
    private final Shuttle shuttle;
    private boolean gameRunning = true;
    private boolean gameFinished = false;

    public RobotGame(String robotName) {
        this.robot   = new Robot(robotName);
        this.shuttle = new Shuttle("EscapePod");
    }


    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void run() {
        System.out.println("Die Expedition beginnt! Viel Erfolg, " + robot.getName() + "!");

    }

    public Robot getRobot() {
        return robot;
    }
}
