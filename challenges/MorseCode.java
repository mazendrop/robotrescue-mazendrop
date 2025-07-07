package challenges;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

import java.util.*;

public class MorseCode implements Challenge {
    private static final long serialVersionUID = 1L;
    private static final Map<Character, String> MORSE_MAP = new HashMap<>();
    private final List<String> wordList = Arrays.asList("SOS", "CODE", "JAVA", "HTW", "HELP");

    private final String originalWord;
    private final String morseCode;
    private int attemptsLeft = 3;
    private boolean solved = false;

    static {
        MORSE_MAP.put('A', ".-"); MORSE_MAP.put('B', "-...");
        MORSE_MAP.put('C', "-.-."); MORSE_MAP.put('D', "-..");
        MORSE_MAP.put('E', "."); MORSE_MAP.put('F', "..-.");
        MORSE_MAP.put('G', "--."); MORSE_MAP.put('H', "....");
        MORSE_MAP.put('I', ".."); MORSE_MAP.put('J', ".---");
        MORSE_MAP.put('K', "-.-"); MORSE_MAP.put('L', ".-..");
        MORSE_MAP.put('M', "--"); MORSE_MAP.put('N', "-.");
        MORSE_MAP.put('O', "---"); MORSE_MAP.put('P', ".--.");
        MORSE_MAP.put('Q', "--.-"); MORSE_MAP.put('R', ".-.");
        MORSE_MAP.put('S', "..."); MORSE_MAP.put('T', "-");
        MORSE_MAP.put('U', "..-"); MORSE_MAP.put('V', "...-");
        MORSE_MAP.put('W', ".--"); MORSE_MAP.put('X', "-..-");
        MORSE_MAP.put('Y', "-.--"); MORSE_MAP.put('Z', "--..");
    }

    public MorseCode() {
        this.originalWord = wordList.get(new Random().nextInt(wordList.size()));
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

    private String toMorse(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toUpperCase().toCharArray()) {
            sb.append(MORSE_MAP.getOrDefault(c, "")).append(" ");
        }
        return sb.toString().trim();
    }
}
