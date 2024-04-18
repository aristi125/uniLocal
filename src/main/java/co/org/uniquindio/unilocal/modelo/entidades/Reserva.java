package co.org.uniquindio.unilocal.modelo.entidades;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Reserva {

    private LocalDateTime fecha;
    private int hora;
    private int cantidadPersonas;
    private String codigoCliente;
    private String codigoNegocio;

}

