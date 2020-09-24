package it.istat.is2.dataset.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "IS2_WORK_SESSION")
public class WorkSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "descr")
    private String descr;
    @Column(name = "name")
    private String name;
    @Column(name = "last_update")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BUSINESS_FUNCTION_ID", nullable = false)
    private BusinessFunction businessFunction;
    
    @OneToMany(mappedBy = "workSession", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DataProcessing> dataProcessings;

    @OneToMany(mappedBy = "workSession", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DatasetFile> datasetFiles;

    @OneToMany(mappedBy = "workSession", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ruleset> ruleSets;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,mappedBy="workSession", orphanRemoval=true)
    private List<Log> logs;
    

    public WorkSession(Long id) {
        super();
        this.id = id;
    }

    public WorkSession() {
        super();
    }
}
