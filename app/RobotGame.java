package app;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import model.Robot;
import model.Room;
import model.Shuttle;
import java.io.Serializable;

public class RobotGame implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Robot robot;
    private final Room[] rooms = new Room[3];
    private final Shuttle shuttle;
    private boolean gameRunning = false;
    private boolean gameFinished = false;

    public RobotGame(String robotName) {
        this.robot = new Robot(robotName);
        this.shuttle = new Shuttle("Zukunft");
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

    public void printStatus() {
        System.out.println("\n=== Roboter-Status ===");
        System.out.println("Name: " + robot.getName());
        System.out.println("Energie: " + robot.getEnergy());
        System.out.println("Schaden: " + robot.getDamage());
        System.out.println("Erfahrung: " + robot.getExperiencePoints());
        System.out.println("Funktionsfähig: " + (robot.isOperational() ? "Ja" : "Nein"));
    }
}
