package jusomejusome.togather.notification.domain;

public enum NotificationType {
    EVENT(0, "event"),
    APPOINTMENT(1, "appointment"),
    COMMENT(2, "comment");

    private final int value;
    private final String type;

    private NotificationType(int value, String type) {
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
