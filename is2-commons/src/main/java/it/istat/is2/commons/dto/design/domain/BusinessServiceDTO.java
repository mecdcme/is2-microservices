package it.istat.is2.commons.dto.design.domain;

import java.io.Serializable;
import java.util.List;

import it.istat.is2.commons.dto.AbstractDomainDTO;
import lombok.Data;

@Data
public class BusinessServiceDTO extends AbstractDomainDTO implements Serializable {

	private static final long serialVersionUID = -34342468741535211L;

	private List<AppRoleDTO> appRoles;

	private List<AppServiceDTO> appServices;

	private GsbpmProcessDTO gsbpmProcess;

}
