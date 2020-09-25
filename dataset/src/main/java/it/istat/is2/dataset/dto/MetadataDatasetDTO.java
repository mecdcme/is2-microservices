package it.istat.is2.dataset.dto;

import it.istat.is2.dataset.domain.DatasetColumn;
import it.istat.is2.dataset.domain.DatasetFile;
import it.istat.is2.dataset.domain.StatisticalVariableCls;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MetadataDatasetDTO {
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

    public static class Builder {
        private List<DatasetColumnDTO> colonne;
        private Long idfile;
        private List<StatisticalVariableClsDTO> variabiliSum;
        private DatasetFileDTO dfile;

        public Builder colonne(List<DatasetColumnDTO> colonne) {
            this.colonne = colonne;
            return this;
        }

        public Builder colonneFromEntity(List<DatasetColumn> colonne) {
            this.colonne = new ArrayList<>(colonne.size());
            colonne.forEach(x -> this.colonne.add(new DatasetColumnDTO.Builder().fromEntity(x).build()));
            return this;
        }

        public Builder idfile(Long idfile) {
            this.idfile = idfile;
            return this;
        }

        public Builder variabiliSum(List<StatisticalVariableClsDTO> variabiliSum) {
            this.variabiliSum = variabiliSum;
            return this;
        }

        public Builder variabiliSumFromEntity(List<StatisticalVariableCls> variabiliSum) {
            this.variabiliSum = new ArrayList<>(variabiliSum.size());
            variabiliSum.forEach(x -> this.variabiliSum.add(new StatisticalVariableClsDTO.Builder().fromEntity(x).build()));
            return this;
        }

        public Builder dfile(DatasetFileDTO dfile) {
            this.dfile = dfile;
            return this;
        }

        public Builder dfileFromEntity(DatasetFile dfile) {
            this.dfile = new DatasetFileDTO.Builder().fromEntity(dfile).build();
            return this;
        }

        public MetadataDatasetDTO build() {
            return new MetadataDatasetDTO(colonne, idfile, variabiliSum, dfile);
        }
    }
}
