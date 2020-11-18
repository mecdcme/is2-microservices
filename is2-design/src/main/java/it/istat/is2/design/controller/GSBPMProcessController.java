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
import it.istat.is2.commons.dto.design.domain.GsbpmProcessDTO;
import it.istat.is2.commons.dto.design.domain.ProcessStepDTO;
import it.istat.is2.commons.request.business.BusinessProcessRequestForm;
import it.istat.is2.design.service.BusinessStepService;
import it.istat.is2.design.service.GsbpmProcessService;
import it.istat.is2.design.translators.Translators;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("design/gsbpm/processes")
public class GSBPMProcessController {

	@Autowired
	private GsbpmProcessService gsbpmProcessService;

	
	@GetMapping
	public List<GsbpmProcessDTO> findAllProcesses() {

		return gsbpmProcessService.findAllProcesses().stream().map(x -> Translators.translate(x))
				.collect(Collectors.toList());

	}
	
	@GetMapping("/{id}")
	public GsbpmProcessDTO getProcess(@PathVariable("id") Long id) {

		return Translators.translate(gsbpmProcessService.findById(id) );

	}
	

	@GetMapping("/{id}/sub-processes")
	public List<GsbpmProcessDTO> getSubProccess(@PathVariable("id") Long idParent) {

		return gsbpmProcessService.findSubProcessesByGsbpmParentProcess(idParent).stream()
				.map(x -> Translators.translate(x)).collect(Collectors.toList());

	}
	
	@GetMapping("/sub-processes")
	public List<GsbpmProcessDTO> findAllSubProcesses() {

		return gsbpmProcessService.findAllSubProcesses().stream().map(x -> Translators.translate(x))
				.collect(Collectors.toList());

	}
}
