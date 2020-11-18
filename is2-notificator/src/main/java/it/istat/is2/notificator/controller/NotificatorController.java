package it.istat.is2.notificator.controller;

import it.istat.is2.notificator.domain.EventEntity;
import it.istat.is2.notificator.dto.EventDTO;
import it.istat.is2.notificator.repository.EventRepository;
import it.istat.is2.notificator.request.EventCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/notificator")
public class NotificatorController {

    @Value("${notificator.queue.create}")
    private String queueName;

    private final RabbitTemplate rabbitTemplate;
    private final EventRepository eventRepository;

    public NotificatorController(RabbitTemplate rabbitTemplate, EventRepository eventRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAll() {
        return ResponseEntity.ok(
               this.eventRepository.findAll().stream().map(x -> new EventDTO.Builder().fromEntity(x).build()).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getSingle(@PathVariable Long id) {
        return ResponseEntity.ok(new EventDTO.Builder().fromEntity(this.eventRepository.findById(id).orElse(new EventEntity())).build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<EventDTO>> getFromType(@PathVariable Integer type) {
        return ResponseEntity.ok(
                this.eventRepository.findAllByType(type).stream().map(x -> new EventDTO.Builder().fromEntity(x).build()).collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody EventCreateRequest request) {
        rabbitTemplate.convertAndSend(queueName, request);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
