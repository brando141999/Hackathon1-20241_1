package dbp.hackathon.Funcion;

import dbp.hackathon.Ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionService {

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Funcion saveFuncion(Funcion funcion) {
        return funcionRepository.save(funcion);
    }

    public Funcion getFuncionById(Long id) {
        return funcionRepository.findById(id).orElse(null);
    }

    public void deleteFuncion(Long id) {
        funcionRepository.deleteById(id);
    }

    public Funcion replaceFuncion(Long id, Funcion funcion) {
        return funcionRepository.findById(id)
                .map(f -> {
                    f.setNombre(funcion.getNombre());
                    f.setDescripcion(funcion.getDescripcion());
                    f.setFecha(funcion.getFecha());
                    f.setDuracion(funcion.getDuracion());
                    f.setPrecio(funcion.getPrecio());
                    f.setStock(funcion.getStock());
                    return funcionRepository.save(f);
                })
                .orElseGet(() -> {
                    funcion.setId(id);
                    return funcionRepository.save(funcion);
                });
    }

    public Funcion updateFuncion(Long id, Funcion funcion) {
        return funcionRepository.findById(id)
                .map(f -> {
                    if (funcion.getNombre() != null) {
                        f.setNombre(funcion.getNombre());
                    }
                    if (funcion.getDescripcion() != null) {
                        f.setDescripcion(funcion.getDescripcion());
                    }
                    if (funcion.getFecha() != null) {
                        f.setFecha(funcion.getFecha());
                    }
                    if (funcion.getDuracion() != null) {
                        f.setDuracion(funcion.getDuracion());
                    }
                    if (funcion.getPrecio() != null) {
                        f.setPrecio(funcion.getPrecio());
                    }
                    if (funcion.getStock() != null) {
                        f.setStock(funcion.getStock());
                    }
                    return funcionRepository.save(f);
                })
                .orElseGet(() -> {
                    funcion.setId(id);
                    return funcionRepository.save(funcion);
                });
    }

    public double calculateTotalEarnings(Long funcionId) {
        Funcion funcion = funcionRepository.findById(funcionId).orElseThrow(() -> new RuntimeException("Funcion not found"));
        return funcion.getTickets().stream()
                .mapToDouble(ticket -> ticket.getCantidad() * funcion.getPrecio())
                .sum();
    }
}
