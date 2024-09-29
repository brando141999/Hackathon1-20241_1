package dbp.hackathon.event;

import dbp.hackathon.Ticket.Ticket;
import dbp.hackathon.event.EmailService;
import dbp.hackathon.event.TicketRedeemedEvent;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TicketRedeemedListener {

    @Autowired
    private EmailService emailService;

    @Async
    @EventListener
    public void handleTicketRedeemedEvent(TicketRedeemedEvent event) throws MessagingException {
        Ticket ticket = event.getTicket();
        String emailContent = generateEmailContent(ticket);
        emailService.sendEmail(ticket.getEstudiante().getEmail(), "Your ticket has been redeemed!", emailContent);
    }

    private String generateEmailContent(Ticket ticket) {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<title>Your ticket has been redeemed!</title>" +
                "</head>" +
                "<body>" +
                "<h1>Your ticket has been redeemed!</h1>" +
                "<p>Hello " + ticket.getEstudiante().getName() + ", your ticket has been successfully redeemed.</p>" +
                "</body>" +
                "</html>";
    }
}