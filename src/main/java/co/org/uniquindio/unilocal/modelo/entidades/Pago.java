package co.org.uniquindio.unilocal.modelo.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Pago {
    private String codigo;
    private LocalDateTime fecha;
    private float totalPagado;
    private String estado;
    private String metodoPago;
}
