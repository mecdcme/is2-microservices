package it.istat.is2.workflowmonitor.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = false)
public class WorkFlowStatutsCreateRequest implements Serializable {

    public static final Long serialVersionUID = 1L;

    private Long workSessionId;
    private Long businessProcessId;
    private Integer status;
}
