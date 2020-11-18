package it.istat.is2.commons.dto.dataset.domain;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DataSetDTO  implements Serializable {

	private static final long serialVersionUID = 5798515776136465850L;
	private List<DatasetColumnDTO> colonne;
    private Long idfile;
    private List<StatisticalVariableClsDTO> variabiliSum;
    private DatasetFileDTO dfile;
    private List<DatasetFileDTO> files;
    private Integer numRighe;

    private DataSetDTO(List<DatasetColumnDTO> colonne, Long idfile, List<StatisticalVariableClsDTO> variabiliSum, DatasetFileDTO dfile, List<DatasetFileDTO> files, Integer numRighe) {
        this.colonne = colonne;
        this.idfile = idfile;
        this.variabiliSum = variabiliSum;
        this.dfile = dfile;
        this.files = files;
        this.numRighe = numRighe;
    }

   
}
