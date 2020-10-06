package it.istat.is2.logger.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@Builder
public class LogDeleteRequest implements Serializable {

    public static final long serialVersionUID = 1L;

    @NotNull @Positive
    private Long sessioneId;

    private String type;
}
