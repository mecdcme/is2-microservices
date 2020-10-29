package it.istat.is2.dataset.dto;

import it.istat.is2.dataset.domain.DatasetColumn;
import it.istat.is2.dataset.domain.DatasetFile;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class DatasetFileDTO {

    private Long id;
    private String fileName;
    private String fileLabel;
    private String fileFormat;
    private String fieldSeparator;
    private Integer totalRows;
    private Date lastUpdate;
    private List<DatasetColumnDTO> columns;

    private DatasetFileDTO(Long id, String fileName, String fileLabel, String fileFormat, String fieldSeparator, Integer totalRows, Date lastUpdate, List<DatasetColumnDTO> columns) {
        this.id = id;
        this.fileName = fileName;
        this.fileLabel = fileLabel;
        this.fileFormat = fileFormat;
        this.fieldSeparator = fieldSeparator;
        this.totalRows = totalRows;
        this.lastUpdate = lastUpdate;
        this.columns = columns;
    }

    public static class Builder {
        private Long id;
        private String fileName;
        private String fileLabel;
        private String fileFormat;
        private String fieldSeparator;
        private Integer totalRows;
        private Date lastUpdate;
        private List<DatasetColumnDTO> columns;

        public Builder fromEntity(DatasetFile entity) {
            this.id = entity.getId();
            this.fileName = entity.getFileName();
            this.fileLabel = entity.getFileLabel();
            this.fileFormat = entity.getFileFormat();
            this.fieldSeparator = entity.getFieldSeparator();
            this.totalRows = entity.getTotalRows();
            this.lastUpdate = entity.getLastUpdate();

            this.columns = new ArrayList<>(entity.getColumns().size());
            entity.getColumns().forEach(c -> this.columns.add(new DatasetColumnDTO.Builder().fromEntity(c).build()));

            return this;
        }

        public DatasetFileDTO build() {
            return new DatasetFileDTO(id, fileName, fileLabel, fileFormat, fieldSeparator, totalRows, lastUpdate, columns);
        }
    }
}
