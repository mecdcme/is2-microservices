package it.istat.is2.commons.dto.design.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.istat.is2.commons.dto.AbstractDomainDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GsbpmProcessDTO extends AbstractDomainDTO implements Serializable {

	private static final long serialVersionUID = -3431146874218535211L;

	private Boolean active;

	private Short orderCode;

	private GsbpmProcessDTO gsbpmProcessParent;

	private List<GsbpmProcessDTO> gsbpmSubProcesses = new ArrayList<>();

	private List<BusinessServiceDTO> businessServices;

}
