package it.istat.is2.runtime.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.istat.is2.commons.dto.worksession.request.CreateWorkSessionRequest;
import it.istat.is2.runtime.service.LogService;
import it.istat.is2.runtime.service.WorkSessionService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageListener {

	private final WorkSessionService workSessionService;
	private final LogService logService;

	@Autowired
	public MessageListener(WorkSessionService workSessionService, LogService logService) {
		this.workSessionService = workSessionService;
		this.logService = logService;
	}

	@RabbitListener(queues = "worksession_create")
	public void newWorkSession(CreateWorkSessionRequest request) {
		log.info("CreateWorkSessionRequest : {}", request);
		try {
			Long newWorkSessionId = workSessionService.newWorkSession(request.getUserId(), request.getDescr(),
					request.getName(), request.getBusinessFunctionId());
			logService.save("Work Session " + request.getName() + " created ", request.getUserId(), newWorkSessionId);
		} catch (Exception e) {
			logService.save("Error " + " WorkSession: " + e.getMessage(), request.getUserId());
		}
		log.info("Done");
	}

	@RabbitListener(queues = "worksession_delete")
	public void deleteWSession(Long idWSession) {
		log.info("deleteWSession : {}", idWSession);
		try {
			workSessionService.deleteWorkSession(idWSession);
		} catch (Exception e) {
			logService.save("Error deleting WorkSession: " + e.getMessage(), idWSession);
		}
		log.info("Done");
	}
}
