package it.istat.is2.dataset.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Map;

@Data
public class ValoriRequest {
    @NotNull @Positive
    private Long dfile;

    private String parametri;

    private Integer length;

    private Integer start;

    private Integer draw;

    @NotNull @NotEmpty
    private Map<String, String> allParams;
}
