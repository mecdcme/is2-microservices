package it.istat.is2.workflowmonitor.enums;

public enum ProcessStatus {
    CREATED(10),
    STARTED(20),
    ENDED(30),
    ERROR(900);

    private int code;

    ProcessStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ProcessStatus fromCode(int code) {
        switch (code) {
            case 10:
                return ProcessStatus.CREATED;
            case 20:
                return ProcessStatus.STARTED;
            case 30:
                return ProcessStatus.ENDED;
            case 900:
                return ProcessStatus.ERROR;
            default:
                throw new IllegalStateException("No values for code : " + code);
        }
    }
}
