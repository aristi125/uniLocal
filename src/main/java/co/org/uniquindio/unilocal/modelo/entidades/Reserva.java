package co.org.uniquindio.unilocal.modelo.entidades;

import co.org.uniquindio.unilocal.modelo.documentos.Cliente;
import co.org.uniquindio.unilocal.modelo.documentos.Negocio;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reserva {

    private LocalDateTime fecha;
    private int hora;
    private int cantidadPersonas;
    private String codigoCliente;
    private String codigoNegocio;

}

