/**
 * Enum representing celebrities in the Hollywood Squares game.
 * Modern Java 21 enum with enhanced switch expressions.
 */
public enum Celebrity {
    TOM_CRUISE(1, "Tom Cruise", "tomCruise.jpg"),
    MARIAH_CAREY(2, "Mariah Carey", "mariahCarey.jpg"),
    DEREK_JETER(3, "Derek Jeter", "derekJeter.jpg"),
    BRAD_PITT(4, "Brad Pitt", "bradPitt.jpg"),
    ANGELINA_JOLIE(5, "Angelina Jolie", "angelinaJolie.jpg"),
    RIHANNA(6, "Rihanna", "rihanna.jpg"),
    LADY_GAGA(7, "Lady Gaga", "ladyGaGa.jpg"),
    CAMERON_DIAZ(8, "Cameron Diaz", "cameronDiaz.jpg"),
    BARACK_OBAMA(9, "Barack Obama", "barackObama.jpg");

    private final int block;
    private final String displayName;
    private final String imagePath;

    Celebrity(int block, String displayName, String imagePath) {
        this.block = block;
        this.displayName = displayName;
        this.imagePath = imagePath;
    }

    public int getBlock() {
        return block;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public static Celebrity fromBlock(int block) {
        return switch (block) {
            case 1 -> TOM_CRUISE;
            case 2 -> MARIAH_CAREY;
            case 3 -> DEREK_JETER;
            case 4 -> BRAD_PITT;
            case 5 -> ANGELINA_JOLIE;
            case 6 -> RIHANNA;
            case 7 -> LADY_GAGA;
            case 8 -> CAMERON_DIAZ;
            case 9 -> BARACK_OBAMA;
            default -> throw new IllegalArgumentException("Invalid block: " + block);
        };
    }

    public static String[] getDisplayNames() {
        return java.util.Arrays.stream(values())
            .map(Celebrity::getDisplayName)
            .toArray(String[]::new);
    }
}
