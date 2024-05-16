package co.org.uniquindio.unilocal.dto.comentario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

//esta clase es para registrar un comentario
public record RegistroComentarioDTO(

    @NotNull LocalDateTime fecha,

    @NotNull int calificacion,

    @NotBlank String codigoCliente,

    @NotBlank String codigoNegocio,

    @NotBlank String mensaje
) {
}
