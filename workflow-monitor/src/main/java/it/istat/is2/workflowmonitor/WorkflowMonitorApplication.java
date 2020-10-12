package it.istat.is2.workflowmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WorkflowMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkflowMonitorApplication.class, args);
    }

}
