package challenges;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.util.*;

public class SecretCode implements Challenge {
    private static final long serialVersionUID = 1L;
    private final int shift = 11;
    private final List<String> codewords = Arrays.asList("LAB", "CODE", "JAVA", "HTW", "ROBOT");
    private final String originalWord;
    private final String encryptedWord;
    private int attemptsLeft = 3;
    private boolean solved = false;

    public SecretCode() {
        this.originalWord = codewords.get(new Random().nextInt(codewords.size()));
        this.encryptedWord = caesarEncrypt(originalWord, shift);
    }

    @Override
    public boolean tryCode(String input) {
        if (solved || attemptsLeft <= 0) {
            return false;
        }

        if (input.trim().equalsIgnoreCase(originalWord)) {
            solved = true;
            return true;
        } else {
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
        return "Ein verschlüsseltes Codewort muss durch Caesar-Chiffre entschlüsselt werden.";
    }

    @Override
    public String getHint() {
        return "Hinweis: Caesar-Verschlüsselung mit Rückversatz von " + shift + ". Verschlüsselter Code: " + encryptedWord;
    }

    private String caesarEncrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                char shifted = (char) (((c - 'A' + shift) % 26) + 'A');
                result.append(shifted);
            } else {
                result.append(c); // for non-letters
            }
        }
        return result.toString();
    }
}
