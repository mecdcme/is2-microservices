package it.istat.is2.workflowmonitor.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "is2_workflow_status_history", schema = "is2", catalog = "")
public class WorkflowStatusHistoryEntity {
    private long id;
    private long workflowStatusId;
    private long workSessionId;
    private long businessFunctionId;
    private int status;
    private Timestamp dttIns;
    private String userIns;
    private Timestamp dttUpd;
    private String userUpd;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "workflow_status_id")
    public long getWorkflowStatusId() {
        return workflowStatusId;
    }

    public void setWorkflowStatusId(long workflowStatusId) {
        this.workflowStatusId = workflowStatusId;
    }

    @Basic
    @Column(name = "work_session_id")
    public long getWorkSessionId() {
        return workSessionId;
    }

    public void setWorkSessionId(long workSessionId) {
        this.workSessionId = workSessionId;
    }

    @Basic
    @Column(name = "business_function_id")
    public long getBusinessFunctionId() {
        return businessFunctionId;
    }

    public void setBusinessFunctionId(long businessFunctionId) {
        this.businessFunctionId = businessFunctionId;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "dtt_ins")
    public Timestamp getDttIns() {
        return dttIns;
    }

    public void setDttIns(Timestamp dttIns) {
        this.dttIns = dttIns;
    }

    @Basic
    @Column(name = "user_ins")
    public String getUserIns() {
        return userIns;
    }

    public void setUserIns(String userIns) {
        this.userIns = userIns;
    }

    @Basic
    @Column(name = "dtt_upd")
    public Timestamp getDttUpd() {
        return dttUpd;
    }

    public void setDttUpd(Timestamp dttUpd) {
        this.dttUpd = dttUpd;
    }

    @Basic
    @Column(name = "user_upd")
    public String getUserUpd() {
        return userUpd;
    }

    public void setUserUpd(String userUpd) {
        this.userUpd = userUpd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkflowStatusHistoryEntity that = (WorkflowStatusHistoryEntity) o;
        return id == that.id &&
                workflowStatusId == that.workflowStatusId &&
                workSessionId == that.workSessionId &&
                businessFunctionId == that.businessFunctionId &&
                status == that.status &&
                Objects.equals(dttIns, that.dttIns) &&
                Objects.equals(userIns, that.userIns) &&
                Objects.equals(dttUpd, that.dttUpd) &&
                Objects.equals(userUpd, that.userUpd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workflowStatusId, workSessionId, businessFunctionId, status, dttIns, userIns, dttUpd, userUpd);
    }
}
