package co.org.uniquindio.unilocal.dto.comentario;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;


//NOTA: Esta clase es para obtener el comentario con todos sus atributos EXCEPTO otros
// es para listar en el historial
public record ItemListaComentariosDTO (
        @NotBlank String id,
        LocalDateTime fecha,
        @NotBlank String mensaje

) {
}
