package challenges;

/**
 * Die Klasse {@code MorseCode} ist eine Challenge, bei der ein zufällig gewähltes Wort
 * in Morsecode umgewandelt wird. Der Benutzer muss das ursprüngliche Wort erraten.
 * Die Morsezeichen werden ohne Verwendung einer Map verwaltet (konform zu Auflagen).
 *
 * Erlaubt sind 3 Versuche.
 *
 * Beispiel: Morsecode: "--.--.--.--." → Lösung: HELP
 *
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */
public class MorseCode implements Challenge {
    private static final long serialVersionUID = 1L;

    private final String[] wordList = {"SOS", "CODE", "JAVA", "HTW", "HELP"};

    // Parallele Arrays für Buchstaben und ihre Morsezeichen
    private final char[] letters = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M',
            'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
    };

    private final String[] morseCodes = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."
    };

    private final String originalWord;
    private final String morseCode;
    private int attemptsLeft = 3;
    private boolean solved = false;

    /**
     * Konstruktor: Wählt zufällig ein Wort und kodiert es in Morsezeichen.
     */
    public MorseCode() {
        int index = (int) (Math.random() * wordList.length);
        this.originalWord = wordList[index];
        this.morseCode = toMorse(originalWord);
    }

    @Override
    public boolean tryCode(String input) {
        if (solved || attemptsLeft <= 0) return false;

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
        return "Ein Codewort ist in Morsezeichen verschlüsselt und soll entschlüsselt werden.";
    }

    @Override
    public String getHint() {
        return "Morsecode: " + morseCode + " → Tipp: Verwende das internationale Morsealphabet.";
    }

    /**
     * Diese Methode wandelt ein Wort in Morsezeichen um.
     */
    private String toMorse(String word) {
        String morse = "";
        char[] chars = word.toUpperCase().toCharArray();

        for (char c : chars) {
            for (int i = 0; i < letters.length; i++) {
                if (letters[i] == c) {
                    morse = morse + morseCodes[i] + " ";
                    break;
                }
            }
        }

        if (!morse.isEmpty()) {
            morse = morse.substring(0, morse.length() - 1);  // Letztes Leerzeichen entfernen
        }

        return morse;
    }
}
