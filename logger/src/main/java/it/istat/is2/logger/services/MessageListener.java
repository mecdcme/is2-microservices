package it.istat.is2.logger.services;

import it.istat.is2.logger.domain.LogEntity;
import it.istat.is2.logger.domain.WorkSessionEntity;
import it.istat.is2.logger.model.LogCreateRequest;
import it.istat.is2.logger.model.LogDeleteRequest;
import it.istat.is2.logger.repository.LogRepository;
import it.istat.is2.logger.repository.WorkSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@Slf4j
public class MessageListener {

    private final LogRepository logRepository;
    private final WorkSessionRepository workSessionRepository;

    @Autowired
    public MessageListener(LogRepository logRepository, WorkSessionRepository workSessionRepository) {
        this.logRepository = logRepository;
        this.workSessionRepository = workSessionRepository;
    }

    @RabbitListener(queues = "createLogQueue")
    public void createLogEntry(LogCreateRequest in) {
        log.info("Create Log entry from this request : {}", in);
        LogEntity entity = new LogEntity();

        WorkSessionEntity workSessionEntity = this.workSessionRepository.findById(in.getIdSessione()).orElseThrow(RuntimeException::new);
        entity.setWorkSession(workSessionEntity);
        entity.setMsg(in.getMsg());
        entity.setMsgTime(new Timestamp(new Date().getTime()));
        entity.setType ( in.getTipo() == null || in.getTipo().equals("") ? "OUT" : in.getTipo());

        this.logRepository.save(entity);
    }

    @RabbitListener(queues = "deleteLogQueue")
    public void deleteLogEntry(LogDeleteRequest in) {
        log.info("Delete Log entry from this request : {}", in);

        if (in.getTipo() != null && !in.getTipo().equals("")) {
            this.logRepository.deleteByWorkSessionAndType(in.getIdSessione(), in.getTipo());
        } else {
            this.logRepository.deleteByWorkSession(in.getIdSessione());
        }
    }
}
