package it.istat.is2.design.controller;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.istat.is2.commons.dto.design.domain.BusinessFunctionDTO;
import it.istat.is2.commons.request.business.BusinessServiceRequestForm;
import it.istat.is2.design.service.BusinessFunctionService;
import it.istat.is2.design.translators.Translators;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "design/business/function", produces = MediaType.APPLICATION_JSON_VALUE)
public class BusinessFunctionController {
 
	@Autowired
	private  BusinessFunctionService businessFunctionService;
 

	@GetMapping
	public List<BusinessFunctionDTO> getAllBusinessFunction() {

		return businessFunctionService.findBFunctions().stream().map(x -> Translators.translate(x))
				.collect(Collectors.toList());

	}

	@GetMapping(value = "/{id}")
	public BusinessFunctionDTO getBusinessFunction(@PathVariable("id") Long id) {

		return  businessFunctionService.findBFunctionById(id);

	}

	@PostMapping
	public Long create(@RequestHeader(name = "jwt-auth") final String jwt,
			@RequestBody BusinessServiceRequestForm request) {
		Long newBusinessFunctionId = businessFunctionService.create(jwt,request) ;
		return newBusinessFunctionId;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteWorkSession(@PathVariable("id") Long id) {

		businessFunctionService.deleteBFunction(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/public/test")
	public ResponseEntity<String> testPublic() {
		return ResponseEntity.ok("public method test");
	}
}
