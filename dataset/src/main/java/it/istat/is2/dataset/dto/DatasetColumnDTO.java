package it.istat.is2.dataset.dto;

import it.istat.is2.dataset.domain.DatasetColumn;
import lombok.Getter;

import java.util.List;

@Getter
public class DatasetColumnDTO {

    private Long id;
    private String name;
    private Short orderCode;
    private Integer contentSize;
    private List<String> contents;
    private DatasetFileDTO datasetFile;
    private StatisticalVariableClsDTO variabileType;

    private DatasetColumnDTO(Long id, String name, Short orderCode, Integer contentSize, List<String> contents, DatasetFileDTO datasetFile, StatisticalVariableClsDTO variabileType) {
        this.id = id;
        this.name = name;
        this.orderCode = orderCode;
        this.contentSize = contentSize;
        this.contents = contents;
        this.datasetFile = datasetFile;
        this.variabileType = variabileType;
    }

    public static class Builder {
        private Long id;
        private String name;
        private Short orderCode;
        private Integer contentSize;
        private List<String> contents;
        private DatasetFileDTO datasetFile;
        private StatisticalVariableClsDTO variabileType;

        public Builder fromEntity(DatasetColumn entity) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.orderCode = entity.getOrderCode();
            this.contentSize = entity.getContentSize();
            this.contents = entity.getContents();
            this.datasetFile = new DatasetFileDTO.Builder().fromEntity(entity.getDatasetFile()).build();
            this.variabileType = new StatisticalVariableClsDTO.Builder().fromEntity(entity.getVariabileType()).build();

            return this;
        }

        public DatasetColumnDTO build() {
            return new DatasetColumnDTO(id,name,orderCode,contentSize,contents,datasetFile, variabileType);
        }
    }
}
