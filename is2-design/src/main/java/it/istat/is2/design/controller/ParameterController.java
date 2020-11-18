package it.istat.is2.design.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.istat.is2.commons.dto.design.domain.ProcessStepDTO;
import it.istat.is2.design.service.BusinessStepService;
import it.istat.is2.design.translators.Translators;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("design/parameters")
public class ParameterController {

	private static final String Q_CREATE = "business_service_create";
	private static final String Q_DELETE = "business_service_delete";

	@Autowired
	private BusinessStepService businessStepService;

	@GetMapping
	public ResponseEntity<List<ProcessStepDTO>> getAll() {

		return ResponseEntity.ok(
				businessStepService.findAll().stream().map(x -> Translators.translate(x)).collect(Collectors.toList()));

	}

}
