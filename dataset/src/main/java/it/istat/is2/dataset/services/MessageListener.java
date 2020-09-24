package it.istat.is2.dataset.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener {

    @Autowired
    public MessageListener() {

    }

    @RabbitListener(queues = "${dataset.create_queue_name}")
    public void createStatusEntry(String in) {
        log.info("Create status entry from this request : {}", in);

    }
}
