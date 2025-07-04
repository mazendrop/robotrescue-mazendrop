package challenges;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

public class RiddleChallenge implements Challenge {
    private final String question = "Was hat viele Schlüssel, aber kann keine Türen öffnen?";
    private final String answer = "Klavier";
    private int attemptsLeft = 3;
    private boolean solved = false;

    @Override
    public boolean tryCode(String input) {
        if (solved || attemptsLeft == 0) return false;

        if (input.trim().equalsIgnoreCase(answer)) {
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
        return question;
    }

    @Override
    public String getHint() {
        return "Es ist ein Musikinstrument.";
    }
}
