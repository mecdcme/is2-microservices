package it.istat.is2.workflowmonitor.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "is2_workflow_status", schema = "is2")
public class WorkflowStatusEntity extends BaseEntity{
    private Long id;
    private Long workSessionId;
    private Long businessFunctionId;
    private Integer status;

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkflowStatusEntity that = (WorkflowStatusEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(workSessionId, that.workSessionId) &&
                Objects.equals(businessFunctionId, that.businessFunctionId) &&
                Objects.equals(status, that.status) &&
                Objects.equals(getCreationDate(), that.getCreationDate()) &&
                Objects.equals(getCreatorUser(), that.getCreatorUser()) &&
                Objects.equals(getModifyDate(), that.getModifyDate()) &&
                Objects.equals(getModifierUser(), that.getModifierUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workSessionId, businessFunctionId, status, getCreationDate(), getCreatorUser(), getModifyDate(), getModifierUser());
    }
}
