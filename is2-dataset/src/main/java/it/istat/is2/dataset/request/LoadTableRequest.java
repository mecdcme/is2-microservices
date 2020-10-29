package it.istat.is2.dataset.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoadTableRequest implements Serializable {
    public static final Long serialVersionUID = 1L;

    private String idsessione;
    private String dbschema;
    private String tablename;
    private String[] fields;
}
