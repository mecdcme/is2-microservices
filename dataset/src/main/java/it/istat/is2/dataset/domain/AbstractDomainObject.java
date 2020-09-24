package it.istat.is2.dataset.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractDomainObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	protected Long id;
	@Column(name = "NAME")
	protected String name;
	@Column(name = "DESCR")
	protected String descr;

}
