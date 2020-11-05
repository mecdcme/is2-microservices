package it.istat.is2.commons.dto.worksession.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import it.istat.is2.commons.dto.AbstractDomainDTO;
import it.istat.is2.commons.dto.dataset.domain.DatasetFileDTO;
import it.istat.is2.commons.dto.design.domain.BusinessFunctionDTO;
import it.istat.is2.commons.dto.ruleset.domain.RulesetDTO;
import lombok.Data;

@Data
public class WorkSessionDTO extends AbstractDomainDTO implements Serializable {

	private static final long serialVersionUID = 5663767962017823449L;

	private Date lastUpdate;

	private Long user;

	private BusinessFunctionDTO businessFunction;

	private List<DataProcessingDTO> dataProcessings;

	private List<DatasetFileDTO> datasetFiles;

	private List<RulesetDTO> ruleSets;

	public WorkSessionDTO(Long id) {
		super();
		this.id = id;
	}

	public WorkSessionDTO() {
		super();
	}
}
