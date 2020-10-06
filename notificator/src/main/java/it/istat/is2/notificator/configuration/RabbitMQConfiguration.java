package it.istat.is2.notificator.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
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

    /**
     * Required for executing adminstration functions against an AMQP Broker
     */
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    /**
     * This queue will be declared. This means it will be created if it does not exist. Once declared, you can do something
     * like the following:
     *
     * @RabbitListener(queues = "#{@myDurableQueue}")
     * @Transactional
     * public void handleMyDurableQueueMessage(CustomDurableDto myMessage) {
     *    // Anything you want! This can also return a non-void which will queue it back in to the queue attached to @RabbitListener
     * }
     */
    @Bean
    public Queue createEventQueue() {
        // This queue has the following properties:
        // name: my_durable
        // durable: true
        // exclusive: false
        // auto_delete: false
        return new Queue("createEventQueue", true, false, false);
    }

    @Bean
    public Queue sendEmailQueue() {
        // This queue has the following properties:
        // name: my_durable
        // durable: true
        // exclusive: false
        // auto_delete: false
        return new Queue("sendEmailQueue", true, false, false);
    }


}
