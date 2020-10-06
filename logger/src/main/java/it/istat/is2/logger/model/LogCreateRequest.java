package it.istat.is2.logger.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@ToString
public class LogCreateRequest implements Serializable {

    public static final long serialVersionUID = 1L;

    @NotNull
    @NotEmpty
    private String logContent;

    @NotNull
    @Positive
    private Long sessionId;

    private String type;
}
