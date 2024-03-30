package co.org.uniquindio.unilocal.modelo.entidades;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Reporte {

    private  String codigoNegocio;

    private String nombreNegocio;

    private LocalDateTime fecha;

    private int numReservas;
}
