package it.istat.is2.logger.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "is2_log", schema = "is2", catalog = "")
public class LogEntity {
    private int id;
    private String msg;
    private Timestamp msgTime;
    private String type;
    private WorkSessionEntity workSession;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MSG")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Basic
    @Column(name = "MSG_TIME")
    public Timestamp getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Timestamp msgTime) {
        this.msgTime = msgTime;
    }

    @Basic
    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(name = "work_session_ID")
    private WorkSessionEntity getWorkSession() { return workSession; };

    public void setWorkSession(WorkSessionEntity workSession) { this.workSession = workSession; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEntity logEntity = (LogEntity) o;
        return id == logEntity.id &&
                Objects.equals(msg, logEntity.msg) &&
                Objects.equals(msgTime, logEntity.msgTime) &&
                Objects.equals(type, logEntity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, msg, msgTime, type);
    }
}
