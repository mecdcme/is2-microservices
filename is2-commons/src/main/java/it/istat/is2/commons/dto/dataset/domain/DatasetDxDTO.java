package it.istat.is2.commons.dto.dataset.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class DatasetDxDTO {
    private List<String> columns;
    private List<Map<String, String>> data;
}
