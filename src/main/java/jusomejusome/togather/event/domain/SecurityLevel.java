package jusomejusome.togather.event.domain;

public enum SecurityLevel {
    LEVEL_ONE(1),
    LEVEL_TWO(2),
    LEVEL_THREE(3),
    LEVEL_FOUR(4),
    LEVEL_FIVE(5);

    private final int level;

    SecurityLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }
}