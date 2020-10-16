package it.istat.is2.worksession.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.istat.is2.commons.dto.worksession.request.CreateWorkSessionRequest;
import it.istat.is2.worksession.service.WorkSessionService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageListener {

	private final WorkSessionService workSessionService;

	@Autowired
	public MessageListener(WorkSessionService workSessionService) {
		this.workSessionService = workSessionService;
	}

	@RabbitListener(queues = "worksession_create")
	public void loadInputData(CreateWorkSessionRequest request) throws Exception {
		log.info("CreateWorkSessionRequest : {}", request);
	
		workSessionService.newWorkSession(request.getUserId(), request.getDescr(), request.getName(), request.getBusinessFunctionId());

		log.info("Done");
	}

	@RabbitListener(queues = "worksession_delete")
	public void deleteWSession(Long idWSession) {
		log.info("deleteWSession : {}", idWSession);
		workSessionService.deleteWorkSession(idWSession);
		log.info("Done");
	}
}
