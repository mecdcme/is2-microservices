package it.istat.is2.dataset.domain;

import java.io.Serializable;
import javax.persistence.*;

import it.istat.is2.dataset.domain.RuleCls;
import it.istat.is2.dataset.domain.Ruleset;
import lombok.Data;

@Data
@Entity
@Table(name = "IS2_RULE")
public class Rule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCR")
    private String descr;
    @Column(name = "BLOCKING")
    private Integer blocking;
    @Column(name = "ERROR_CODE")
    private Integer errorCode;
    @Column(name = "ACTIVE")
    private Short active;
    @Column(name = "RULE")
    private String rule;
    @Column(name = "VARIABLE_ID")
    private Integer variableId;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLS_RULE_ID", nullable = true)
    private RuleCls ruleType;

    @ManyToOne
    @JoinColumn(name = "RULESET_ID")
    private Ruleset ruleset;

}
