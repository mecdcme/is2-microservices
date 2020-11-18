package it.istat.is2.dataset.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter

@Entity
@Table(name = "IS2_CLS_DATA_TYPE")
public class DataTypeCls implements Serializable {
    
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	protected Long id;
	@Column(name = "NAME")
	protected String name;
	@Column(name = "DESCR")
	protected String descr;

    public DataTypeCls() {
        super();
    }

    public DataTypeCls(Long id) {
        super();
        this.id = id;
    }

}
