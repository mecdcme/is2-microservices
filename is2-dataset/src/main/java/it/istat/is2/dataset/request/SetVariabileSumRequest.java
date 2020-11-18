package it.istat.is2.dataset.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
public class SetVariabileSumRequest implements Serializable {
    public static final Long serialVersionUID = 1L;

    @NotNull @Positive
    private Long idcol;

    @NotNull @Positive
    private Integer idva;
}
