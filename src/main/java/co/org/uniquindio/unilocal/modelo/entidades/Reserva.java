package co.org.uniquindio.unilocal.modelo.entidades;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reserva {

    private LocalDate fecha;
    private LocalDateTime hora;
    private int cantidadPersonas;
    private String codigoCliente;
    private String codigoNegocio;

}

