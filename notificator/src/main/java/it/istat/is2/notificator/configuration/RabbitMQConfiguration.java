package it.istat.is2.notificator.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue createEventQueue() {
        return new Queue("createEventQueue", true);
    }

}
