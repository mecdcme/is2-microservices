package it.istat.is2.commons.dto.design.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.istat.is2.commons.dto.AbstractDomainDTO;
import lombok.Data;

@Data
public class BusinessProcessDTO extends AbstractDomainDTO implements Serializable {

	private static final long serialVersionUID = -3222589283247782660L;

	private String label;

	private Short order;

	private List<BusinessFunctionDTO> businessFunctions;

	private BusinessProcessDTO businessProcessParent;

	private List<ProcessStepDTO> businessSteps;

	private List<BusinessProcessDTO> businessSubProcesses = new ArrayList<>();

}
