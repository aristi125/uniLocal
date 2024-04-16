package co.org.uniquindio.unilocal.dto.comentario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ComentarioDTO(
    String mensaje,
    String codigoNegocio,
    String codigoCliente,
    LocalDateTime horaComentario,
    String codigoComentario,
    String respuesta
) {
}
