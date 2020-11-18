package it.istat.is2.commons.request;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractRequestForm {
	
	protected Long id;
	
	protected String name;
	
	protected String descr;

}
