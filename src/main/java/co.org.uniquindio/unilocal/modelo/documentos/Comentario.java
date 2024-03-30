package co.org.uniquindio.unilocal.modelo.documentos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.time.LocalDateTime;

@Document("comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigoComemtario;

    private LocalDateTime fecha;

    private int calificacion;

    private String codigoCliente;

    private String codigoNegocio;

    private String mensaje;

    private String respuesta;

}
