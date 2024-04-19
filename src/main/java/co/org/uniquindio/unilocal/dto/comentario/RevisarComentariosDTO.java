package co.org.uniquindio.unilocal.dto.comentario;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RevisarComentariosDTO(
        @NotBlank String mensaje,
        @NotBlank String codigoUsuario,
        @NotBlank String nombreUsuario,
        @NotBlank @Email String email,
        @NotNull LocalDateTime fecha

) {
}