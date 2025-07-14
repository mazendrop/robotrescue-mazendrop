package app;

/**
 * Hauptklasse für die Roboter-Abenteuer-Anwendung.
 * Verwaltet das Hauptmenü, den Spielstart, das Laden/Speichern
 * und die Spielfortsetzung.
 *
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import model.Robot;

public class RobotApp {

    // Dateiname für den Spielstand
    public static final String SAVE_FILE_NAME = "save";

    // Referenz auf das aktuelle Spielobjekt
    private RobotGame game;

    // Gibt an, ob ein Spiel aktiv ist
    private boolean gameRunning = true;

    /**
     * Einstiegspunkt der Anwendung
     * zeigt das Hauptmenü an und verarbeitet Benutzereingaben in einer Schleife
     */

    public static void main(String[] args) {
        System.out.println("Welcome to the robot adventure");
        System.out.println("========================================\n");

        RobotApp app = new RobotApp();

        while (true) {
            app.showMainMenu();
            String choice = app.readUserInput();
            app.handleUserInput(choice);
            System.out.println("====================");
        }
    }

    // Zeigt das Hauptmenü an
    private void showMainMenu() {
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");
        System.out.println("(1) Spiel starten");

        if (isGameRunning()) {
            System.out.println("(2) Spiel fortsetzen");
            System.out.println("(3) Spiel speichern");
        }

        if (hasSavedGame()) {
            System.out.println("(4) Spiel laden");
            System.out.println("(5) Spiel löschen");
        }

        System.out.println("(6) Programm beenden");
        System.out.println("\nBitte wähle eine Option: ");
    }

    /**
     * Liest eine Texteingabe vom Benutzer.
     * return die eingegebene Zeichenkette
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Verarbeitet die Benutzereingabe aus dem Hauptmenü.
     * input die vom Benutzer gewählte Option
     */
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                this.startNewGame();
                break;
            case "2":
                if (isGameRunning()) this.continueGame();
                else System.out.println("Kein laufendes Spiel.");
                break;
            case "3":
                if (isGameRunning()) this.saveGame();
                else System.out.println("Kein Spiel zum Speichern.");
                break;
            case "4":
                if (hasSavedGame()) this.loadGame();
                else System.out.println("Kein gespeichertes Spiel gefunden.");
                break;
            case "5":
                if (hasSavedGame()) this.deleteGame();
                else System.out.println("Kein gespeichertes Spiel zum Löschen.");
                break;
            case "6":
                System.out.println("Spiel wird beendet...");
                System.exit(0);
                break;
            default:
                System.out.println("Ungültige Eingabe! Bitte zwischen 1 und 6 wählen.");
                break;
        }
    }

    // Startet ein neues Spiel und fragt nach dem Namen des Roboters
    private void startNewGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wie soll dein Roboter heißen? ");
        String name = scanner.nextLine();

        this.game = new RobotGame(name);
        continueGame();
    }

    // Führt das laufende Spiel im Spielmenü fort
    private void continueGame() {
        this.game.setGameRunning(true);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWas möchtest du tun?");
            System.out.println("(1) Forschungsstation erkunden");
            System.out.println("(2) Status anzeigen");
            System.out.println("(3) Energie aufladen");
            System.out.println("(4) Reparieren");
            System.out.println("(5) Spiel verlassen");
            System.out.print("Deine Wahl: ");
            String wahl = scanner.nextLine();

            switch (wahl) {
                case "1":
                    game.run();
                    break;
                case "2":
                    game.printMissionStatus();
                    break;
                case "3":
                    int aktuelleEnergie = game.getRobot().getEnergy();
                    int maxEnergie = game.getRobot().getMaxEnergy();
                    int zehnProzent = maxEnergie / 10;
                    int energieFehlt = maxEnergie - aktuelleEnergie;

                    if (energieFehlt <= 0) {
                        System.out.println("Die Energie ist bereits voll.");
                    } else {
                        int aufladen = Math.min(zehnProzent, energieFehlt);
                        game.getRobot().recharge(aufladen);
                        System.out.println("Der Roboter wurde um " + aufladen + " Energie aufgeladen.");
                    }
                    break;
                case "4":
                    game.openMaintenanceMenu();
                    break;
                case "5":
                    System.out.println("Spiel wird verlassen...");
                    return;
                default:
                    System.out.println("Ungültige Eingabe.");
            }

            if (game.isGameFinished()) {
                System.out.println("🎉 Das Spiel wurde erfolgreich beendet. Danke fürs Spielen!");
                break;
            }

        }
    }

    // Löscht die gespeicherte Spieldatei
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Game deleted!");
        }
    }

    // Speichert den aktuellen Spielstand in eine Datei
    private void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            oos.flush();
        } catch (Exception ex) {
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
        }
        System.out.println("Game saved!");
    }

    // Lädt den gespeicherten Spielstand aus der Datei
    private void loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.game = (RobotGame) ois.readObject();
            System.out.println("Game loaded!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while loading the game: " + ex.getMessage());
        }
    }

    /**
     * Prüft, ob ein Spiel gerade läuft.
     * return true, wenn ein Spiel aktiv ist
     */
    private boolean isGameRunning() {
        return game != null;
    }

    /**
     * Prüft, ob das aktuelle Spiel beendet wurde.
     * return true, wenn das Spiel abgeschlossen ist
     */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }

    /**
     * Prüft, ob eine gespeicherte Spieldatei existiert.
     * return true, wenn eine Datei vorhanden ist
     */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }
}
