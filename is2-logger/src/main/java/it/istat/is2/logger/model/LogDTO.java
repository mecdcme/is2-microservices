package it.istat.is2.logger.model;

import it.istat.is2.logger.domain.LogEntity;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public final class LogDTO {

    private int id;
    private String content;
    private Timestamp msgTime;
    private String type;

    private LogDTO(int id, String msg, Timestamp msgTime, String type) {
        this.id = id;
        this.content = msg;
        this.msgTime = msgTime;
        this.type = type;
    }

    public static class Builder {
        private int id;
        private String msg;
        private Timestamp msgTime;
        private String type;

        public Builder fromEntity(LogEntity entity) {
            this.id         = entity.getId();
            this.msg        = entity.getMsg();
            this.msgTime    = entity.getMsgTime();
            this.type       = entity.getType();

            return this;
        }

        public LogDTO build() {
            return new LogDTO(this.id, this.msg, this.msgTime, this.type);
        }
    }
}
