package it.istat.is2.notificator.request;

import it.istat.is2.notificator.domain.EventEntity;
import it.istat.is2.notificator.enums.EventType;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class SendEmailRequest implements Serializable {
    public static final Long serialVersionUID = 1L;

    private Long id;
    private String[] recipients;
    private EventType type;
    private LocalDateTime eventDate;
    private String descriptionSummary;
    private String description;

    private SendEmailRequest(Long id, String[] recipients, EventType type, LocalDateTime eventDate, String descriptionSummary, String description) {
        this.id = id;
        this.recipients = recipients;
        this.type = type;
        this.eventDate = eventDate;
        this.descriptionSummary = descriptionSummary;
        this.description = description;
    }

    public static class Builder {
        private Long id;
        private String[] recipients;
        private EventType type;
        private LocalDateTime eventDate;
        private String descriptionSummary;
        private String description;

        public Builder fromEntity(EventEntity entity) {
            this.id = entity.getId();
            this.type = EventType.fromCode(entity.getType());
            this.eventDate = entity.getEventDate().toLocalDateTime();
            this.description = entity.getDescription();
            this.descriptionSummary = entity.getDescriptionSummary();
            return this;
        }

        public Builder recipients(List<String> recipients) {
            this.recipients = recipients.toArray(new String[0]);
            return this;
        }

        public SendEmailRequest build() {
            return new SendEmailRequest(id, recipients, type, eventDate, descriptionSummary, description);
        }
    }
}
