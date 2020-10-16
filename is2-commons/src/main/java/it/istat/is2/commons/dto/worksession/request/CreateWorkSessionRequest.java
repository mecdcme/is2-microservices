package it.istat.is2.commons.dto.worksession.request;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateWorkSessionRequest implements Serializable {


	private static final long serialVersionUID = 5939182470433987956L;
	Long userId;
	String descr;
	String name;
	Long businessFunctionId;
}
