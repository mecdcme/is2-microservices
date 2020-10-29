package it.istat.is2.notificator.enums;

public enum EventType {
    INFO(0),
    WARNING(10),
    SEVERE(20),
    ERROR(30),
    CRITICAL(100);

    private Integer code;
    EventType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static EventType fromCode(Integer code) {
        switch (code) {
            case 0: return EventType.INFO;
            case 10: return EventType.WARNING;
            case 20: return EventType.SEVERE;
            case 30: return EventType.ERROR;
            case 100: return EventType.CRITICAL;
            default: throw new IllegalArgumentException("EventType with code "+code+ " not exists");
        }
    }
}
