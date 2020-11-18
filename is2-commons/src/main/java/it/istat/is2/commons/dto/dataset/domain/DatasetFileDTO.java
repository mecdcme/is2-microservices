package it.istat.is2.commons.dto.dataset.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class DatasetFileDTO implements Serializable {

	private static final long serialVersionUID = -9076707553610163991L;
	private Long id;
	private String fileName;
	private String fileLabel;
	private String fileFormat;
	private String fieldSeparator;
	private Integer totalRows;
	private Date lastUpdate;
	private List<DatasetColumnDTO> columns;
	private DataSetTypeDTO dataType;

	public DatasetFileDTO(Long id, String fileName, String fileLabel, String fileFormat, String fieldSeparator,
			Integer totalRows, Date lastUpdate, List<DatasetColumnDTO> columns) {
		this.id = id;
		this.fileName = fileName;
		this.fileLabel = fileLabel;
		this.fileFormat = fileFormat;
		this.fieldSeparator = fieldSeparator;
		this.totalRows = totalRows;
		this.lastUpdate = lastUpdate;
		this.columns = columns;
	}

	public DatasetFileDTO() {
		super();
	}

}
