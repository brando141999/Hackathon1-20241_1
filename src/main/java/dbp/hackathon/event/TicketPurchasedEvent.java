package dbp.hackathon.event;

import dbp.hackathon.Ticket.Ticket;
import org.springframework.context.ApplicationEvent;

public class TicketPurchasedEvent extends ApplicationEvent {

    private final Ticket ticket;

    public TicketPurchasedEvent(Object source, Ticket ticket) {
        super(source);
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

}
