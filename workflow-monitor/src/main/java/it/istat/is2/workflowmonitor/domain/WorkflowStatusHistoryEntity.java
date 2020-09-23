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
    private Timestamp creationDate;
    private String creatorUser;
    private Timestamp modifyDate;
    private String modifierUser;

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    @Column(name = "creationDate")
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp dttIns) {
        this.creationDate = dttIns;
    }

    @Basic
    @Column(name = "creatorUser")
    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String userIns) {
        this.creatorUser = userIns;
    }

    @Basic
    @Column(name = "modifyDate")
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp dttUpd) {
        this.modifyDate = dttUpd;
    }

    @Basic
    @Column(name = "modifierUser")
    public String getModifierUser() {
        return modifierUser;
    }

    public void setModifierUser(String userUpd) {
        this.modifierUser = userUpd;
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
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(creatorUser, that.creatorUser) &&
                Objects.equals(modifyDate, that.modifyDate) &&
                Objects.equals(modifierUser, that.modifierUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workflowStatusId, workSessionId, businessFunctionId, status, creationDate, creatorUser, modifyDate, modifierUser);
    }
}
