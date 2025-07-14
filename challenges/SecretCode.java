package challenges;

/**
 * Die Klasse {@code SecretCode} stellt eine Challenge dar, bei der ein zufälliges Wort
 * mit der Caesar-Chiffre verschlüsselt wird. Der Benutzer muss das ursprüngliche Wort erraten.
 *
 * Die Verschiebung beträgt 11 Buchstaben nach rechts im Alphabet.
 * Der Benutzer hat 3 Versuche.
 *
 * Beispiel: Verschlüsseltes Wort: "WLM" → Lösung: "LAB"
 *
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */
public class SecretCode implements Challenge {
    private static final long serialVersionUID = 1L;

    // Anzahl der Buchstaben, um die verschoben wird (Caesar-Chiffre)
    private final int shift = 11;

    // Mögliche Wörter (als Array statt List)
    private final String[] codewords = {"LAB", "CODE", "JAVA", "HTW", "ROBOT"};

    private final String originalWord;
    private final String encryptedWord;

    private int attemptsLeft = 3;
    private boolean solved = false;

    /**
     * Konstruktor: Wählt ein zufälliges Wort und verschlüsselt es mit Caesar-Chiffre.
     */
    public SecretCode() {
        int index = (int) (Math.random() * codewords.length);
        this.originalWord = codewords[index];
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
        return "Hinweis: Caesar-Verschlüsselung mit Rückversatz von " + shift +
                ". Verschlüsselter Code: " + encryptedWord;
    }

    /**
     * Verschlüsselt das übergebene Wort mithilfe der Caesar-Verschiebung.
     * StringBuilder wurde entfernt und durch einfachen String ersetzt.
     *
     * @param text Ursprüngliches Wort
     * @param shift Anzahl Stellen zur Verschiebung
     * @return Verschlüsseltes Wort
     */
    private String caesarEncrypt(String text, int shift) {
        String result = "";
        char[] chars = text.toUpperCase().toCharArray();

        for (char c : chars) {
            if (c >= 'A' && c <= 'Z') {
                char shifted = (char) (((c - 'A' + shift) % 26) + 'A');
                result = result + shifted;
            } else {
                result = result + c;
            }
        }

        return result;
    }
}
