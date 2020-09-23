package it.istat.is2.workflowmonitor.response;

import it.istat.is2.workflowmonitor.domain.WorkflowStatusEntity;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
public class WorkFlowStatusDTO {
    private Long id;
    private Long workSessionId;
    private Long businessFunctionId;
    private Integer status;
    private Date dttIns;
    private String userIns;
    private Date dttUpd;
    private String userUpd;

    private WorkFlowStatusDTO(Long id, Long workSessionId, Long businessFunctionId, Integer status, Date dttIns, String userIns, Date dttUpd, String userUpd) {
        this.id = id;
        this.workSessionId = workSessionId;
        this.businessFunctionId = businessFunctionId;
        this.status = status;
        this.dttIns = dttIns;
        this.userIns = userIns;
        this.dttUpd = dttUpd;
        this.userUpd = userUpd;
    }

    public static class Builder {
        private Long id;
        private Long workSessionId;
        private Long businessFunctionId;
        private Integer status;
        private Date dttIns;
        private String userIns;
        private Date dttUpd;
        private String userUpd;

        public Builder fromEntity(WorkflowStatusEntity entity) {
            this.id = entity.getId();
            this.workSessionId = entity.getWorkSessionId();
            this.businessFunctionId = entity.getBusinessFunctionId();
            this.status = entity.getStatus();
            this.dttIns = entity.getCreationDate();
            this.userIns = entity.getCreatorUser();
            this.dttUpd = entity.getModifyDate();
            this.userUpd = entity.getModifierUser();
            return this;
        }

        public WorkFlowStatusDTO build() {
            return new WorkFlowStatusDTO(id, workSessionId, businessFunctionId, status, dttIns, userIns, dttUpd, userUpd);
        }
    }
}
