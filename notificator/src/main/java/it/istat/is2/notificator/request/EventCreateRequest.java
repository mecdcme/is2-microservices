package it.istat.is2.notificator.request;

import it.istat.is2.notificator.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateRequest implements Serializable {

    public static final Long serialVersionUID = 1L;

    @NotNull
    private EventType type;

    @NotNull @NotEmpty
    private String descriptionSummary;

    @NotNull @NotEmpty
    private String description;
}

