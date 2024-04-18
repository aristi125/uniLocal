package co.org.uniquindio.unilocal.modelo.entidades;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reserva {

    @EqualsAndHashCode.Include // Incluye el atributo en el método equals y hashCode

    private LocalDate fecha;
    private LocalTime hora;
    private int cantidadPersonas;
    private String codigoCliente;
    private String codigoNegocio;

}

