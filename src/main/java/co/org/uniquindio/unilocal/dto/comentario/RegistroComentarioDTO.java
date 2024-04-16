package co.org.uniquindio.unilocal.dto.comentario;

import java.time.LocalDateTime;


public record RegistroComentarioDTO(

    LocalDateTime fecha,

    int calificacion,

    String codigoCliente,

    String codigoNegocio,

    String mensaje,

    String respuesta
) {
}
