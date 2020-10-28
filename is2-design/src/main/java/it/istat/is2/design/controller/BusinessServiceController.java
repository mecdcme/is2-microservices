package it.istat.is2.design.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/business/service")
public class BusinessServiceController {

	private static final String Q_CREATE = "business_service_create";
	private static final String Q_DELETE = "business_service_delete";

}
