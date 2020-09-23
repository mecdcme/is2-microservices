package it.istat.is2.workflowmonitor.request;

import lombok.Data;

@Data
public class WorkFlowStatutsCreateRequest {
    private Long workSessionId;
    private Long businessProcessId;
    private Integer status;
}
