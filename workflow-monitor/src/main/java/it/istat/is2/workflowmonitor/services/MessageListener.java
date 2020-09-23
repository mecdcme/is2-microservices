package it.istat.is2.workflowmonitor.services;

import it.istat.is2.workflowmonitor.domain.WorkflowStatusEntity;
import it.istat.is2.workflowmonitor.domain.WorkflowStatusHistoryEntity;
import it.istat.is2.workflowmonitor.repository.WorkFlowStatusHistoryRepository;
import it.istat.is2.workflowmonitor.repository.WorkFlowStatusRepository;
import it.istat.is2.workflowmonitor.request.WorkFlowStatutsCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Component
@Slf4j
public class MessageListener {

    private final WorkFlowStatusRepository workFlowStatusRepository;
    private final WorkFlowStatusHistoryRepository workFlowStatusHistoryRepository;

    @Autowired
    public MessageListener(WorkFlowStatusRepository workFlowStatusRepository, WorkFlowStatusHistoryRepository workFlowStatusHistoryRepository) {
        this.workFlowStatusRepository = workFlowStatusRepository;
        this.workFlowStatusHistoryRepository = workFlowStatusHistoryRepository;
    }

    @RabbitListener(queues = "${monitor.create_queue_name}")
    public void createStatusEntry(WorkFlowStatutsCreateRequest in) {
        log.info("Create status entry from this request : {}", in);

        Optional<WorkflowStatusEntity> lastRecord = this.workFlowStatusRepository.findTopByWorkSessionIdAndBusinessFunctionIdOrderByCreationDateDesc(in.getWorkSessionId(), in.getBusinessProcessId());
        lastRecord.ifPresent(r -> {
            log.info("found previous record, insert history");
            WorkflowStatusHistoryEntity h = new WorkflowStatusHistoryEntity();
            h.setBusinessFunctionId(r.getBusinessFunctionId());
            h.setWorkSessionId(r.getWorkSessionId());
            h.setCreationDate((new Timestamp(r.getCreationDate().getTime())));
            h.setModifyDate(new Timestamp(r.getModifyDate().getTime()));
            h.setStatus(r.getStatus());
            h.setWorkflowStatusId(r.getId());
            h.setCreatorUser(r.getCreatorUser());
            h.setModifierUser(r.getModifierUser());

            log.info("history to save : {}", h);
            this.workFlowStatusHistoryRepository.save(h);
        });

        WorkflowStatusEntity e;

        if (lastRecord.isPresent()) {
            log.info("update existing records");
            e = lastRecord.get();
            e.setStatus(in.getStatus());
        }
        else {
            log.info("create new record");
            e = new WorkflowStatusEntity();
            e.setBusinessFunctionId(in.getBusinessProcessId());
            e.setStatus(in.getStatus());
            e.setWorkSessionId(in.getWorkSessionId());
        }

        log.info("workflow status to save : {}", e);
        this.workFlowStatusRepository.save(e);
    }

}
