package it.istat.is2.commons.dto.dataset.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticalVariableClsDTO implements Serializable{
 
	private static final long serialVersionUID = -4962985025993910811L;
	private Integer id;
    private String name;
    private String descr;
    private Short order;
    private String nameEng;
    private String nameIta;

    public StatisticalVariableClsDTO(Integer id, String name, String descr, Short order, String nameEng, String nameIta) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.order = order;
        this.nameEng = nameEng;
        this.nameIta = nameIta;
    }


}
