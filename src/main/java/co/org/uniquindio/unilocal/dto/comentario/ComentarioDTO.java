package co.org.uniquindio.unilocal.dto.comentario;

import java.time.LocalDate;

public record ComentarioDTO(
    String mensaje,
    String codigoNegocio,
    String codigoCliente,
    LocalDate horaComentario
) {
}
