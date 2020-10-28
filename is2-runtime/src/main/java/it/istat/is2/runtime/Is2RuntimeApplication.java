package it.istat.is2.runtime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Is2RuntimeApplication {

    @Value("${spring.datasource.platform}")
    private String datasourcePlatform;

    public static void main(String[] args) {
        SpringApplication.run(Is2RuntimeApplication.class, args);
    }
 

}
