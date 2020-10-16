package it.istat.is2.commons.dto.dataset.domain;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MetadataDatasetDTO implements Serializable{

	private static final long serialVersionUID = -8890760500114724999L;
	private List<DatasetColumnDTO> colonne;
    private Long idfile;
    private List<StatisticalVariableClsDTO> variabiliSum;
    private DatasetFileDTO dfile;


    private MetadataDatasetDTO(List<DatasetColumnDTO> colonne, Long idfile, List<StatisticalVariableClsDTO> variabiliSum, DatasetFileDTO dfile) {
        this.colonne = colonne;
        this.idfile = idfile;
        this.variabiliSum = variabiliSum;
        this.dfile = dfile;
    }

   
}
