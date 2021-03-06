package it.istat.is2.workflowmonitor.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${monitor.create_queue_name}")
    private String queueName;

    @Bean
    public Queue workflowStatusQueue() {
        return new Queue(queueName, true);
    }

}
