package co.org.uniquindio.unilocal.modelo.entidades;

import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocio;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class HistorialRevision {

    private String descripcion;

    private EstadoNegocio estado;

    private LocalDateTime fecha;

    private String codigoModerador;



}
