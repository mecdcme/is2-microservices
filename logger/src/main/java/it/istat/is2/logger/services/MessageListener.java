package it.istat.is2.logger.services;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.istat.is2.logger.domain.LogEntity;
import it.istat.is2.logger.exceptions.NULLUserException;
import it.istat.is2.logger.model.LogCreateRequest;
import it.istat.is2.logger.model.LogDeleteRequest;
import it.istat.is2.logger.repository.LogRepository;
import it.istat.is2.logger.repository.WorkSessionRepository;
import lombok.extern.slf4j.Slf4j;

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

		try {
			if (in.getUserId() == null)
				throw new NULLUserException();

			entity.setUser(in.getUserId());
			entity.setWorkSession(in.getSessionId());
			entity.setMsg(in.getLogContent());
			entity.setMsgTime(new Timestamp(new Date().getTime()));
			entity.setType(in.getType() == null || in.getType().equals("") ? "OUT" : in.getType());

			this.logRepository.save(entity);

		} catch (NULLUserException e) {
			log.error("user with id {} null, log ignored", in.toString());
		}
	}

	@RabbitListener(queues = "deleteLogQueue")
	public void deleteLogEntry(LogDeleteRequest in) {
		log.info("Delete Log entry from this request : {}", in);

		if (in.getType() != null && !in.getType().equals("")) {
			this.logRepository.deleteByWorkSessionAndType(in.getSessioneId(), in.getType());
		} else if (in.getSessioneId() != null) {
			this.logRepository.deleteByWorkSession(in.getSessioneId());
		} else {
			this.logRepository.deleteByUser(in.getUserId());
		}
	}

}
