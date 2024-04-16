package co.org.uniquindio.unilocal.dto.comentario;

import java.time.LocalDateTime;

//esta clase es para registrar un comentario
public record RegistroComentarioDTO(

    LocalDateTime fecha,

    int calificacion,

    String codigoCliente,

    String codigoNegocio,

    String mensaje,

    String respuesta
) {
}
