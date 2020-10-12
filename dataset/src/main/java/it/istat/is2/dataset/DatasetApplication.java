package it.istat.is2.dataset;

import it.istat.is2.dataset.dao.SqlGenericDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class DatasetApplication {

    @Value("${spring.datasource.platform}")
    private String datasourcePlatform;

    public static void main(String[] args) {
        SpringApplication.run(DatasetApplication.class, args);
    }

    @Bean
    public SqlGenericDao sqlGenericDaoResolver() {
        return SqlGenericDao.getSqlGenericDAOFactory(datasourcePlatform);
    }

}
