/**
 * Record representing a board position with row and column.
 * Modern Java 21 record pattern.
 */
public record Position(int row, int column) {
    public Position {
        if (row < 0 || row > 2 || column < 0 || column > 2) {
            throw new IllegalArgumentException(
                "Invalid position: row=%d, column=%d. Must be between 0 and 2.".formatted(row, column)
            );
        }
    }

    public static Position fromBlock(int block) {
        return switch (block) {
            case 1 -> new Position(0, 0);
            case 2 -> new Position(0, 1);
            case 3 -> new Position(0, 2);
            case 4 -> new Position(1, 0);
            case 5 -> new Position(1, 1);
            case 6 -> new Position(1, 2);
            case 7 -> new Position(2, 0);
            case 8 -> new Position(2, 1);
            case 9 -> new Position(2, 2);
            default -> throw new IllegalArgumentException("Invalid block: " + block);
        };
    }
}
