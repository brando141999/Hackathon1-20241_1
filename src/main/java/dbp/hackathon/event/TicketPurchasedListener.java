package dbp.hackathon.event;

import dbp.hackathon.Ticket.Ticket;
import dbp.hackathon.event.EmailService;
import dbp.hackathon.event.TicketPurchasedEvent;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TicketPurchasedListener {

    @Autowired
    private EmailService emailService;

    @Async
    @EventListener
    public void handleTicketPurchasedEvent(TicketPurchasedEvent event) throws MessagingException {
        Ticket ticket = event.getTicket();
        String emailContent = generateEmailContent(ticket);
        emailService.sendEmail(ticket.getEstudiante().getEmail(), "¡Gracias por tu compra!", emailContent);
    }

    private String generateEmailContent(Ticket ticket) {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<title>¡Gracias por tu compra!</title>" +
                "</head>" +
                "<body>" +
                "<h1>¡Gracias por tu compra!</h1>" +
                "<p>¡Hola " + ticket.getEstudiante().getName() + "! Te informamos que tu compra ha sido exitosa. A continuación, te presentamos los detalles de tu compra:</p>" +
                "<ul>" +
                "<li>Nombre de la película: " + ticket.getFuncion().getNombre() + "</li>" +
                "<li>Fecha de la función: " + ticket.getFuncion().getFecha() + "</li>" +
                "<li>Cantidad de entradas: " + ticket.getCantidad() + "</li>" +
                "<li>Precio total: " + (ticket.getFuncion().getPrecio() * ticket.getCantidad()) + "</li>" +
                "<li>Código QR: <img src=\"" + ticket.getQr() + "\"></li>" +
                "</ul>" +
                "<p>¡No olvides llevar tu código QR impreso o en tu dispositivo móvil para poder ingresar a la función! ¡Te esperamos!</p>" +
                "</body>" +
                "</html>";
    }
}
