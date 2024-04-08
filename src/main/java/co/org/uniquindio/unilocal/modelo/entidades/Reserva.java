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

    private  String codigoNegocio;

    private String nombreNegocio;

    private LocalDateTime fechaReserva;

    private  int horaReserva;

    private int numPersonas;
}

