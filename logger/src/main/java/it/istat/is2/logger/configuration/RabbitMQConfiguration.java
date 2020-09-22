package it.istat.is2.logger.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue createLogQueue() {
        return new Queue("createLogQueue", true);
    }

    @Bean
    public Queue deleteLogQueue() {
        return new Queue("deleteLogQueue", true);
    }
}
