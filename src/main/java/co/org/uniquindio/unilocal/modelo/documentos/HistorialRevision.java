package co.org.uniquindio.unilocal.modelo.documentos;

import co.org.uniquindio.unilocal.modelo.enumeracion.EstadoNegocioModerador;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("historial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HistorialRevision {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String descripcion;

    private EstadoNegocioModerador estado;

    private LocalDateTime fecha;

    private String codigoModerador;

    private String codigoNegocio;
}
