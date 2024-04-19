package co.org.uniquindio.unilocal.dto.comentario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


//NOTA: Esta clase es para obtener el comentario con todos sus atributos EXCEPTO otros
// es para listar en el historial
public record ItemListaComentariosDTO (
        @NotBlank String id,
        @NotNull LocalDateTime fecha,
        @NotBlank String mensaje

) {
}
