package it.istat.is2.notificator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix="email")
public class EmailRecipientsConfiguration {

    private final List<String> recipients = new ArrayList<>();

    public List<String> getRecipients() {
        return recipients;
    }
}
