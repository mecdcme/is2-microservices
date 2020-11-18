package it.istat.is2.commons.dto.dataset.domain;

import java.io.Serializable;

import lombok.Data;
@Data
public class DataSetTypeDTO implements Serializable {

 
	private static final long serialVersionUID = -7835687844479530349L;
	
	protected Long id;
 
	protected String name;

	protected String descr;

}
