package dbp.hackathon.event;

import dbp.hackathon.Ticket.Ticket;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TicketRedeemedEvent extends ApplicationEvent {

    private final Ticket ticket;

    public TicketRedeemedEvent(Object source, Ticket ticket) {
        super(source);
        this.ticket = ticket;
    }

}
