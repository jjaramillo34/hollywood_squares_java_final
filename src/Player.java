/**
 * Modern Player class using Java 21 features.
 * Encapsulates player data with immutable name and mark.
 */
public final class Player {
    private final String name;
    private final boolean isHuman;
    private final int mark;
    private int numOfMarks;
    private int score;

    public Player(String name, int mark, boolean isHuman) {
        this.name = name;
        this.mark = mark;
        this.isHuman = isHuman;
        this.numOfMarks = 0;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getMark() {
        return mark;
    }

    public int getScore() {
        return score;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public int getNumOfMarks() {
        return numOfMarks;
    }

    public void updateScore(int points) {
        this.score += points;
    }

    public void incrementMarks() {
        this.numOfMarks++;
    }

    public void resetMarks() {
        this.numOfMarks = 0;
    }

    @Override
    public String toString() {
        return "Player[name=%s, mark=%d, score=%d, isHuman=%b]".formatted(name, mark, score, isHuman);
    }
}
