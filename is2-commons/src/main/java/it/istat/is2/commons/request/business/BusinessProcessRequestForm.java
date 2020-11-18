package it.istat.is2.commons.request.business;

import java.io.Serializable;

import it.istat.is2.commons.request.AbstractRequestForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BusinessProcessRequestForm extends AbstractRequestForm implements Serializable {

	private static final long serialVersionUID = 5663717962017823449L;

	private String label;

	private Short order;

}
