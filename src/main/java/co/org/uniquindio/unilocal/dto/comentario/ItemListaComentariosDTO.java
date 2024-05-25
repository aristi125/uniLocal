package co.org.uniquindio.unilocal.dto.comentario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record ItemListaComentariosDTO (
        @NotBlank String id,
        @NotBlank String mensaje

) {
}
