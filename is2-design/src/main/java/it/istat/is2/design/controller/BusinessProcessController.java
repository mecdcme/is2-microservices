package it.istat.is2.design.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.istat.is2.commons.dto.design.domain.BusinessProcessDTO;
import it.istat.is2.commons.request.business.BusinessProcessRequestForm;
import it.istat.is2.design.service.BusinessProcessService;
import it.istat.is2.design.translators.Translators;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("design/business/processes")
public class BusinessProcessController {

	@Autowired
	private BusinessProcessService businessProcessService;

	@GetMapping
	public List<BusinessProcessDTO> getAllBProcess() {

		return businessProcessService.findAll().stream().map(x -> Translators.translate(x))
				.collect(Collectors.toList());

	}
	

	@GetMapping("/{id}")
	public BusinessProcessDTO getProccess(@PathVariable("id") Long id) {

		return Translators.translate(businessProcessService.findBProcessById(id));

	}

	@GetMapping("/{id}/sub-processes")
	public ResponseEntity<List<BusinessProcessDTO>> getSubProccess(@PathVariable("id") Long idParent) {

		return ResponseEntity.ok(businessProcessService.findAllSubProcessesByParent(idParent).stream()
				.map(x -> Translators.translate(x)).collect(Collectors.toList()));

	}

	@PostMapping
	public BusinessProcessDTO create(@RequestHeader(name = "jwt-auth") final String jwt,
			@RequestBody BusinessProcessRequestForm request) {
		return businessProcessService.create(jwt, request);

	}

	@PostMapping("/{id}/sub-processes")
	public BusinessProcessDTO createSubProcess(@RequestHeader(name = "jwt-auth") final String jwt,
			@RequestBody BusinessProcessRequestForm request) {
		return businessProcessService.create(jwt, request);

	}

}
