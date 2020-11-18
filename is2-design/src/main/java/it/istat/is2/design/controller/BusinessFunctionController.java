package it.istat.is2.design.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.istat.is2.commons.dto.design.domain.BusinessFunctionDTO;
import it.istat.is2.commons.dto.design.domain.BusinessServiceDTO;
import it.istat.is2.design.service.BusinessFunctionService;
import it.istat.is2.design.service.BusinessServiceService;
import it.istat.is2.design.translators.Translators;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("design/business/function")
public class BusinessFunctionController {

	private static final String Q_CREATE = "business_function_create";
	private static final String Q_DELETE = "business_function_delete";

	private final RabbitTemplate rabbitTemplate;

	private final BusinessFunctionService businessFunctionService;

	@Autowired
	public BusinessFunctionController(RabbitTemplate rabbitTemplate, BusinessFunctionService businessFunctionService) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.businessFunctionService = businessFunctionService;
	}

	@GetMapping
	public ResponseEntity<List<BusinessFunctionDTO>> getAllBusinessFunction() {

		return ResponseEntity.ok(businessFunctionService.findBFunctions().stream()
				.map(x -> Translators.translate(x)).collect(Collectors.toList()));

	}

}
