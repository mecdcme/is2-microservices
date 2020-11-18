package it.istat.is2.notificator.services;

import it.istat.is2.notificator.configuration.EmailRecipientsConfiguration;
import it.istat.is2.notificator.repository.EventRepository;
import it.istat.is2.notificator.request.SendEmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailSender {

    @Value("${notificator.queue.email}")
    private String queueName;

    private final EmailRecipientsConfiguration emailRecipientsConfiguration;
    private final RabbitTemplate rabbitTemplate;
    private final EventRepository eventRepository;

    public EmailSender(EmailRecipientsConfiguration emailRecipientsConfiguration, RabbitTemplate rabbitTemplate, EventRepository eventRepository) {
        this.emailRecipientsConfiguration = emailRecipientsConfiguration;
        this.rabbitTemplate = rabbitTemplate;
        this.eventRepository = eventRepository;
    }

    @Scheduled(fixedDelay = 5000L)
    public void sendEventToEmail() {
        log.info("start fetch event to send email");
        var unsentEmail = this.eventRepository.findUnsentEmail();
        log.info("found {} event to send email", unsentEmail.size());
        unsentEmail.forEach(e -> this.rabbitTemplate.convertAndSend(queueName, new SendEmailRequest.Builder().recipients(this.emailRecipientsConfiguration.getRecipients()).fromEntity(e).build()));
        log.info("done");
    }
}
