package it.istat.is2.commons.dto.dataset.request;

import lombok.Data;

@Data
public class LoadTableRequest {
    private String idsessione;
    private String dbschema;
    private String tablename;
    private String[] fields;
}
