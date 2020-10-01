package it.istat.is2.notificator.services;

import it.istat.is2.notificator.domain.EventEntity;
import it.istat.is2.notificator.repository.EventRepository;
import it.istat.is2.notificator.request.EventCreateRequest;
import it.istat.is2.notificator.request.SendEmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Component
@Slf4j
public class MessageListener {

    private final EventRepository eventRepository;
    private final JavaMailSender emailSender;

    public MessageListener(EventRepository eventRepository, JavaMailSender emailSender) {
        this.eventRepository = eventRepository;
        this.emailSender = emailSender;
    }

    @RabbitListener(queues = "${notificator.queue.create}")
    public void createLogEntry(EventCreateRequest in) {

        EventEntity entity = new EventEntity();
        entity.setDescription(in.getDescription());
        entity.setDescriptionSummary(in.getDescriptionSummary());
        entity.setType(in.getType().getCode());

        entity.setEmailSent(0);
        entity.setEventDate(new Timestamp(new Date().getTime()));

        this.eventRepository.save(entity);
    }

    @RabbitListener(queues = "${notificator.queue.email}")
    public void sendEmail(SendEmailRequest in) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply-is2@istat.it");
        message.setTo(in.getRecipients());
        message.setSubject("Event type : " + in.getType()+ " on date : " + in.getEventDate());
        message.setText(in.getDescriptionSummary() + "\n" + in.getDescription());

        EventEntity entity = this.eventRepository.findById(in.getId()).orElseThrow();
        entity.setEmailSent(1);
        entity.setEventDate(new Timestamp(new Date().getTime()));
        this.eventRepository.save(entity);

        emailSender.send(message);
    }
}
