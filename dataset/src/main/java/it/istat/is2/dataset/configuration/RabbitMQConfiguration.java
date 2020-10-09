package it.istat.is2.dataset.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
@Slf4j
public class RabbitMQConfiguration {

    @Value("${spring.rabbitmq.host:localhost}")
    private String hostname;

    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(hostname, 5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        log.info("Creating connection factory with: {}", hostname);

        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public Queue createEventQueue() {
        return new Queue("createEventQueue", true, false, false);
    }

    @Bean
    public Queue sendEmailQueue() {
        return new Queue("sendEmailQueue", true, false, false);
    }
}
