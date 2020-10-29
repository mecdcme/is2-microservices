package it.istat.is2.workflowmonitor.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntity {
    @CreatedBy
    @Column(name = "creatorUser")
    private String creatorUser;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDate")
    private Date creationDate;

    @LastModifiedBy
    @Column(name = "modifierUser")
    private String modifierUser;

    @LastModifiedDate
    @Column(name = "modifyDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
}
