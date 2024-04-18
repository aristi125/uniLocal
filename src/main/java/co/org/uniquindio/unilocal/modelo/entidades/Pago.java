package co.org.uniquindio.unilocal.modelo.entidades;

import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoPago;
import co.org.uniquindio.unilocal.modelo.enumeracion.MetodoPago;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Pago {

    private LocalDateTime fecha;
    private float totalPagado;
    private EstadoPago estado;
    private MetodoPago metodoPago;
}
