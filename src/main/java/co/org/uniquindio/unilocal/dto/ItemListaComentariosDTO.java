package co.org.uniquindio.unilocal.dto;

import java.time.LocalDateTime;

public record ItemListaComentariosDTO(
        String codigoComemtario,
        LocalDateTime fecha,
        String mensaje
) {
}
