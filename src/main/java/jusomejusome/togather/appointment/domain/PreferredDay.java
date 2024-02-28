package jusomejusome.togather.appointment.domain;

public enum PreferredDay {
    WEEKDAY(0, "weekday"),
    WEEKEND(1, "weekend");

    private final int value;
    private final String type;

    private PreferredDay(int value, String type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
