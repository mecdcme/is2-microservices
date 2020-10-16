package it.istat.is2.worksession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WorkSessionApplication {

    @Value("${spring.datasource.platform}")
    private String datasourcePlatform;

    public static void main(String[] args) {
        SpringApplication.run(WorkSessionApplication.class, args);
    }
 

}
