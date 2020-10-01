package it.istat.is2.notificator.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Event", schema = "is2_notificator")
public class EventEntity {
    private Long id;
    private int type;
    private Timestamp eventDate;
    private String descriptionSummary;
    private String description;
    private int emailSent;
    private Timestamp emailSentDate;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "event_date")
    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    @Basic
    @Column(name = "description_summary")
    public String getDescriptionSummary() {
        return descriptionSummary;
    }

    public void setDescriptionSummary(String descriptionSummary) {
        this.descriptionSummary = descriptionSummary;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "email_sent")
    public int getEmailSent() {
        return emailSent;
    }

    public void setEmailSent(int emailSent) {
        this.emailSent = emailSent;
    }

    @Basic
    @Column(name = "email_sent_date")
    public Timestamp getEmailSentDate() {
        return emailSentDate;
    }

    public void setEmailSentDate(Timestamp emailSentDate) {
        this.emailSentDate = emailSentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventEntity that = (EventEntity) o;
        return id == that.id &&
                type == that.type &&
                emailSent == that.emailSent &&
                Objects.equals(eventDate, that.eventDate) &&
                Objects.equals(descriptionSummary, that.descriptionSummary) &&
                Objects.equals(description, that.description) &&
                Objects.equals(emailSentDate, that.emailSentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, eventDate, descriptionSummary, description, emailSent, emailSentDate);
    }
}
