package it.istat.is2.workflowmonitor.response;

import it.istat.is2.workflowmonitor.domain.WorkflowStatusEntity;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class WorkFlowStatusDTO {
    private Long id;
    private Long workSessionId;
    private Long businessFunctionId;
    private Integer status;
    private Timestamp dttIns;
    private String userIns;
    private Timestamp dttUpd;
    private String userUpd;

    private WorkFlowStatusDTO(Long id, Long workSessionId, Long businessFunctionId, Integer status, Timestamp dttIns, String userIns, Timestamp dttUpd, String userUpd) {
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
        private Timestamp dttIns;
        private String userIns;
        private Timestamp dttUpd;
        private String userUpd;

        public Builder fromEntity(WorkflowStatusEntity entity) {
            this.id = entity.getId();
            this.workSessionId = entity.getWorkSessionId();
            this.businessFunctionId = entity.getBusinessFunctionId();
            this.status = entity.getStatus();
            this.dttIns = entity.getDttIns();
            this.userIns = entity.getUserIns();
            this.dttUpd = entity.getDttUpd();
            this.userUpd = entity.getUserUpd();
            return this;
        }

        public WorkFlowStatusDTO build() {
            return new WorkFlowStatusDTO(id, workSessionId, businessFunctionId, status, dttIns, userIns, dttUpd, userUpd);
        }
    }
}
