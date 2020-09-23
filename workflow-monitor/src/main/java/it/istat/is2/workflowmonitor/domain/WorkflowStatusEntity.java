package it.istat.is2.workflowmonitor.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "is2_workflow_status", schema = "is2", catalog = "")
public class WorkflowStatusEntity {
    private Long id;
    private Long workSessionId;
    private Long businessFunctionId;
    private Integer status;
    private Timestamp dttIns;
    private String userIns;
    private Timestamp dttUpd;
    private String userUpd;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "work_session_id")
    public Long getWorkSessionId() {
        return workSessionId;
    }

    public void setWorkSessionId(Long workSessionId) {
        this.workSessionId = workSessionId;
    }

    @Basic
    @Column(name = "business_function_id")
    public Long getBusinessFunctionId() {
        return businessFunctionId;
    }

    public void setBusinessFunctionId(Long businessFunctionId) {
        this.businessFunctionId = businessFunctionId;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
        WorkflowStatusEntity that = (WorkflowStatusEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(workSessionId, that.workSessionId) &&
                Objects.equals(businessFunctionId, that.businessFunctionId) &&
                Objects.equals(status, that.status) &&
                Objects.equals(dttIns, that.dttIns) &&
                Objects.equals(userIns, that.userIns) &&
                Objects.equals(dttUpd, that.dttUpd) &&
                Objects.equals(userUpd, that.userUpd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workSessionId, businessFunctionId, status, dttIns, userIns, dttUpd, userUpd);
    }
}
