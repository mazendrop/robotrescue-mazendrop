package challenges;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.util.*;

public class BinaryCode implements Challenge {
    private static final long serialVersionUID = 1L;
    private final String[] binaryNumbers = new String[3];
    private final int correctCode;
    private int attemptsLeft = 3;
    private boolean solved = false;

    public BinaryCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int num = random.nextInt(16); // 0–15
            binaryNumbers[i] = String.format("%04d", Integer.parseInt(Integer.toBinaryString(num)));
            sb.append(num);
        }

        correctCode = Integer.parseInt(sb.toString());
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
