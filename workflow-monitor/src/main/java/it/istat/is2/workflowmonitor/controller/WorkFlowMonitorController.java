package it.istat.is2.workflowmonitor.controller;

import it.istat.is2.workflowmonitor.request.WorkFlowStatutsCreateRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitor")
public class WorkFlowMonitorController {

    @Value("${monitor.create_queue_name}")
    private String queueName;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public WorkFlowMonitorController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/all")
    public ResponseEntity<Void> m1 () {
        throw new IllegalStateException("not implemented yet");
    }

    @GetMapping("/{worksession_id}")
    public ResponseEntity<Void> m2 () {
        throw new IllegalStateException("not implemented yet");
    }

    @GetMapping("/{worksession_id}/{business_process_id}")
    public ResponseEntity<Void> m3 () {
        throw new IllegalStateException("not implemented yet");
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody WorkFlowStatutsCreateRequest request) {
        rabbitTemplate.convertAndSend(queueName, request);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
