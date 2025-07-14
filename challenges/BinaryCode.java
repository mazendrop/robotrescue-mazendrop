package challenges;

/**
 * Die Klasse {BinaryCode} stellt eine Challenge dar, bei der ein dreistelliger
 * Zahlencode aus drei Binärzahlen ermittelt werden muss.
 * Der Nutzer muss die Binärzahlen in Dezimalzahlen umwandeln und die Ziffern zusammensetzen.
 *
 * Beispiel: Binärzahlen 0101 - 1110 - 0000 → Dezimal: 5 - 14 - 0 → Code: 5140
 *
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.util.Random;

public class BinaryCode implements Challenge {
    private static final long serialVersionUID = 1L;

    private final String[] binaryNumbers = new String[3];
    private final int correctCode;
    private int attemptsLeft = 3;
    private boolean solved = false;

    /**
     * Konstruktor erzeugt drei Binärzahlen (jeweils 4 Bit) und generiert daraus einen Code.
     */
    public BinaryCode() {
        Random random = new Random();
        String codeAsText = "";

        for (int i = 0; i < 3; i++) {
            int num = random.nextInt(16);  // Werte von 0 bis 15
            binaryNumbers[i] = String.format("%04d", Integer.parseInt(Integer.toBinaryString(num)));
            codeAsText = codeAsText + num;
        }

        correctCode = Integer.parseInt(codeAsText);
    }

    @Override
    public boolean tryCode(String input) {
        if (solved || attemptsLeft <= 0) return false;

        try {
            int userCode = Integer.parseInt(input.trim());
            if (userCode == correctCode) {
                solved = true;
                return true;
            } else {
                attemptsLeft--;
                return false;
            }
        } catch (NumberFormatException e) {
            attemptsLeft--;
            return false;
        }
    }

    @Override
    public boolean isSolved() {
        return solved;
    }

    @Override
    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    @Override
    public String getDescription() {
        return "Ein dreistelliger Zahlencode soll aus Binärzahlen ermittelt werden.";
    }

    @Override
    public String getHint() {
        return "Binärzahlen: " + String.join(" - ", binaryNumbers) +
                " → Tipp: In Dezimal umwandeln und zusammensetzen.";
    }
}
