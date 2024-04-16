package co.org.uniquindio.unilocal.dto.comentario;

import java.time.LocalDateTime;

public record RespuestaComentarioDTO (
        String codigoComentario,
        String codigoNegocio,
        String respuesta
) {
        }