package it.istat.is2.commons.dto.dataset.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SetVariabileSumRequest {
    @NotNull @Positive
    private Long idcol;

    @NotNull @Positive
    private Integer idva;
}
