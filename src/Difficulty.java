/**
 * Enum representing game difficulty levels for Hollywood Squares.
 * Uses modern Java 21 sealed interface pattern.
 */
public enum Difficulty {
    EASY("Easy", 1),
    MEDIUM("Medium", 2),
    HARD("Hard", 3);

    private final String displayName;
    private final int level;

    Difficulty(String displayName, int level) {
        this.displayName = displayName;
        this.level = level;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getLevel() {
        return level;
    }

    public static Difficulty fromInput(String input) {
        return switch (input.toUpperCase()) {
            case "E" -> EASY;
            case "M" -> MEDIUM;
            case "H" -> HARD;
            default -> throw new IllegalArgumentException("Invalid difficulty: " + input);
        };
    }
}
