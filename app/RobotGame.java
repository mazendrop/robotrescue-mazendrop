package app;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import games.Game;
import games.Nim;
import games.TicTacToe;
import model.*;
import challenges.*;

import java.io.Serializable;
import java.util.Scanner;

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

        Challenge[] challenges = new Challenge[] {
                new BinaryCode(),
                new MorseCode(),
                new SecretCode()
        };

        for (int i = 0; i < rooms.length; i++) {
            Artifact artifact = shuttle.getArtifacts()[i];
            rooms[i] = new Room(i + 1, challenges[i], artifact);
        }
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

    public void run() {
        System.out.println("\n>>>>> Starte Missionsrunde...");
        System.out.println("Die Expedition beginnt! Viel Erfolg, " + robot.getName() + "!");

        int energieVerlust = (int) (robot.getEnergy() * 0.10);
        robot.recharge(-energieVerlust);

        int zufall = (int) (Math.random() * 100);
        Scanner scanner = new Scanner(System.in);

        if (zufall < 50) {
            System.out.println("Die Erkundung war ereignislos. Der Roboter setzt die Suche fort.");
        } else if (zufall < 75) {
            System.out.println("Ein Feind nähert sich! Was möchtest du tun?");
            System.out.println("(1) Kämpfen");
            System.out.println("(2) Spiel spielen");
            System.out.println("(3) Fliehen");
            String wahl = scanner.nextLine();

            switch (wahl) {
                case "1":
                    Enemy enemy = Math.random() < 0.5 ? new DroneEnemy("Drohne", 50) : new AlienEnemy("Alien", 60);
                    enemy.fight(robot);
                    break;
                case "2":
                    playMiniGame();
                    break;
                case "3":
                    if (Math.random() < 0.10) {
                        System.out.println("Flucht erfolgreich! Der Roboter entkommt dem Gegner.");
                    } else {
                        System.out.println("Flucht fehlgeschlagen! Der Roboter muss kämpfen.");
                        enemy = Math.random() < 0.5 ? new DroneEnemy("Drohne", 50) : new AlienEnemy("Alien", 60);
                        enemy.fight(robot);
                    }
                    break;
                default:
                    System.out.println("Ungültige Wahl. Der Roboter zieht sich zurück.");
            }

        } else {
            Room roomToOpen = chooseRandomClosedRoom();
            if (roomToOpen == null) {
                System.out.println("Alle Räume wurden bereits geöffnet.");
                return;
            }

            Challenge challenge = roomToOpen.getChallenge();
            System.out.println("Der Roboter stößt auf eine verschlossene Tür...");
            System.out.println("Challenge: " + challenge.getDescription());
            System.out.println("Hinweis: " + challenge.getHint());
            System.out.print("Deine Antwort: ");
            String antwort = scanner.nextLine();

            if (challenge.tryCode(antwort)) {
                roomToOpen.setOpen(true);
                Artifact artifact = roomToOpen.getArtifact();
                if (artifact != null) {
                    artifact.setFound(true);
                }

                System.out.println("Challenge bestanden! Der Raum wird geöffnet.");
                System.out.println("Der Roboter findet das Artefakt '" + artifact.getName() + "' und gewinnt Energie.");
                robot.recharge(20);
            } else {
                System.out.println("Challenge nicht bestanden. Der Roboter zieht sich zurück.");
            }
        }
    }

    public void printMissionStatus() {
        System.out.println("\n=== Missionsstatus ===");
        printStatus();

        System.out.println("\n--- Shuttle-Status ---");
        for (Artifact artifact : shuttle.getArtifacts()) {
            if (artifact != null && artifact.isFound()) {
                System.out.println("Gefundenes Ersatzteil: " + artifact.getName());
            } else {
                System.out.println("Fehlendes Ersatzteil");
            }
        }

        System.out.println("\n--- Raum-Übersicht ---");
        for (Room room : rooms) {
            String zustand = room.isOpen() ? "geöffnet" : "nicht geöffnet";
            System.out.print("Raum " + room.getNumber() + ": " + zustand);
            if (room.isOpen() && room.getChallenge() != null && room.getChallenge().isSolved()) {
                System.out.print(" (Challenge gelöst)");
            }
            System.out.println();
        }
    }

    public boolean installArtifactsToShuttle() {
        boolean allesInstalliert = true;
        for (Artifact artifact : shuttle.getArtifacts()) {
            if (artifact.isFound() && !artifact.isInstalled()) {
                artifact.install();
            }
            if (!artifact.isInstalled()) {
                allesInstalliert = false;
            }
        }
        return allesInstalliert && shuttle.isReadyToLaunch();
    }

    public void openMaintenanceMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n🛠 Wartungsmenü");
            System.out.println("(1) Roboter reparieren");
            System.out.println("(2) Artefakte installieren");
            System.out.println("(3) Zurück");
            System.out.print("Deine Wahl: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Wie viel Schaden reparieren? ");
                    int schaden = Integer.parseInt(scanner.nextLine());
                    int vorher = robot.getDamage();
                    robot.repairDamage(schaden);
                    System.out.println("Der Roboter wurde um " + (vorher - robot.getDamage()) + " Punkte repariert.");
                    break;

                case "2":
                    if (robot.getExperiencePoints() < 50) {
                        System.out.println("Mindestens 50 Erfahrungspunkte nötig!");
                        break;
                    }

                    for (Room room : rooms) {
                        Artifact artifact = room.getArtifact();
                        if (room.isOpen() && artifact.isFound() && !artifact.isInstalled()) {
                            artifact.install();
                            System.out.println(artifact.getName() + " wurde installiert!");
                        }
                    }

                    if (shuttle.isReadyToLaunch()) {
                        System.out.println("Alle Artefakte installiert! Shuttle bereit zum Start. Spiel erfolgreich beendet 🚀");
                        setGameFinished(true);
                    }
                    break;

                case "3":
                    return;

                default:
                    System.out.println("Ungültige Eingabe!");
            }
        }
    }

    private void playMiniGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wähle ein Spiel:");
        System.out.println("(1) TicTacToe");
        System.out.println("(2) Nim");
        System.out.print("Deine Wahl: ");
        String input = scanner.nextLine();

        Game game = input.equals("1") ? new TicTacToe() : new Nim();

        while (!game.isFinished()) {
            game.playNextRound();
        }

        if (game.isWon()) {
            System.out.println("Du hast das Spiel gewonnen! +20 Erfahrungspunkte.");
            robot.addExperiencePoints(20);
        } else if (game.isLost()) {
            System.out.println("Du hast das Spiel verloren. Keine Belohnung.");
        } else if (game.isTie()) {
            System.out.println("Unentschieden! +10 Erfahrungspunkte.");
            robot.addExperiencePoints(10);
        }
    }

    private Room chooseRandomClosedRoom() {
        return java.util.Arrays.stream(rooms)
                .filter(r -> !r.isOpen())
                .findAny()
                .orElse(null);
    }
}
