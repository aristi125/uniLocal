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

    @EqualsAndHashCode.Include // Incluye el atributo en el método equals y hashCode
    private String codigo;

    private LocalDateTime fecha;
    private int hora;
    private int cantidadPersonas;
    private Cliente cliente;
    private Negocio negocio;

}

