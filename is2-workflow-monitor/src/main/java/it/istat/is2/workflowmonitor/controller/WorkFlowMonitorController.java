package it.istat.is2.workflowmonitor.controller;

import it.istat.is2.workflowmonitor.repository.WorkFlowStatusRepository;
import it.istat.is2.workflowmonitor.request.WorkFlowStatutsCreateRequest;
import it.istat.is2.workflowmonitor.response.WorkFlowStatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/monitor")
public class WorkFlowMonitorController {

    @Value("${monitor.create_queue_name}")
    private String queueName;

    private final RabbitTemplate rabbitTemplate;
    private final WorkFlowStatusRepository workFlowStatusRepository;

    @Autowired
    public WorkFlowMonitorController(RabbitTemplate rabbitTemplate, WorkFlowStatusRepository workFlowStatusRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.workFlowStatusRepository = workFlowStatusRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkFlowStatusDTO>> findAll () {
        log.info("call WorkFlowMonitorController.findAll");

        var r = this.workFlowStatusRepository.findAll()
                .stream()
                .map(x -> new WorkFlowStatusDTO.Builder().fromEntity(x).build())
                .collect(Collectors.toList());
        log.info("result size = {}", r.size());
        return ResponseEntity.ok(r);

    }

    @GetMapping("/{worksession_id}")
    public ResponseEntity<List<WorkFlowStatusDTO>> findAllWorkSessionId (
            @PathVariable("worksession_id") Long workSessionId)
    {
        log.info("call WorkFlowMonitorController.findAllWorkSessionId with parameter : {}", workSessionId);
        return ResponseEntity.ok( this.workFlowStatusRepository.findAllByWorkSessionIdOrderByCreationDate(workSessionId)
                .stream()
                .map(x -> new WorkFlowStatusDTO.Builder().fromEntity(x).build())
                .collect(Collectors.toList()));

    }

    @GetMapping("/{worksession_id}/{business_process_id}")
    public ResponseEntity<List<WorkFlowStatusDTO>> findAllWorkSessionIdAndBusinessProcess (
            @PathVariable("worksession_id") Long workSessionId,
            @PathVariable("business_process_id") Long businessProcessId)
    {
        log.info("call WorkFlowMonitorController.findAllWorkSessionIdAndBusinessProcess with parameter : {}, {}", workSessionId, businessProcessId);
        return ResponseEntity.ok( this.workFlowStatusRepository.findAllByWorkSessionIdAndBusinessFunctionIdOrderByCreationDate(workSessionId, businessProcessId)
                .stream()
                .map(x -> new WorkFlowStatusDTO.Builder().fromEntity(x).build())
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody WorkFlowStatutsCreateRequest request) {
        log.info("call WorkFlowMonitorController.create with request body : {}", request);
        rabbitTemplate.convertAndSend(queueName, request);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
