package it.istat.is2.commons.dto.dataset.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class DatasetColumnDTO implements Serializable {

	private static final long serialVersionUID = 375618579909689167L;
	private Long id;
	private String name;
	private Short orderCode;
	private Integer contentSize;
	private List<String> contents;
	private DatasetFileDTO datasetFile;
	private StatisticalVariableClsDTO variabileType;

	private DatasetColumnDTO(Long id, String name, Short orderCode, Integer contentSize, List<String> contents,
			DatasetFileDTO datasetFile, StatisticalVariableClsDTO variabileType) {
		this.id = id;
		this.name = name;
		this.orderCode = orderCode;
		this.contentSize = contentSize;
		this.contents = contents;
		this.datasetFile = datasetFile;
		this.variabileType = variabileType;
	}

}
