package it.istat.is2.notificator.dto;

import it.istat.is2.notificator.domain.EventEntity;
import it.istat.is2.notificator.enums.EventType;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class EventDTO {
    private Long id;
    private EventType type;
    private Timestamp eventDate;
    private String descriptionSummary;
    private String description;
    private int emailSent;
    private Timestamp emailSentDate;

    private EventDTO(Long id, EventType type, Timestamp eventDate, String descriptionSummary, String description, int emailSent, Timestamp emailSentDate) {
        this.id = id;
        this.type = type;
        this.eventDate = eventDate;
        this.descriptionSummary = descriptionSummary;
        this.description = description;
        this.emailSent = emailSent;
        this.emailSentDate = emailSentDate;
    }

    public static class Builder {
        private Long id;
        private EventType type;
        private Timestamp eventDate;
        private String descriptionSummary;
        private String description;
        private int emailSent;
        private Timestamp emailSentDate;

        public Builder fromEntity(EventEntity entity) {
            this.id = entity.getId();
            this.type = EventType.fromCode(entity.getType());
            this.eventDate = entity.getEventDate();
            this.descriptionSummary = entity.getDescriptionSummary();
            this.description = entity.getDescription();
            this.emailSent = entity.getEmailSent();
            this.emailSentDate = entity.getEmailSentDate();

            return this;
        }

        public EventDTO build() {
            return new EventDTO(id, type, eventDate, descriptionSummary, description, emailSent, emailSentDate);
        }
    }
}
