package it.istat.is2.commons.dto.dataset.request;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class LoadInputDataRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private MultipartFile fileName;
    private String name;
    private String descrizione;
    private String tipo;
    private String labelFile;
    private String labelCodeRule;
    private Long fileType;
    private int numeroCampi;
    private String delimiter;
    private String identificativo;
    private String idsessione;
    private String classificazione;
    private Integer skipFirstLine;
}
