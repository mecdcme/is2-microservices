package it.istat.is2.workflowmonitor.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class WorkFlowStatutsCreateRequest implements Serializable {

    public static final Long serialVersionUID = 1L;

    private Long workSessionId;
    private Long businessProcessId;
    private Integer status;
}
