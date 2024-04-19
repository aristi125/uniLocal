package co.org.uniquindio.unilocal.dto.comentario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

//Esta clase es para obtener el comentario con todos sus atributos
//es para hacer procesos
public record DetalleComentarioDTO(
        @NotBlank String codigoComentario,
        @NotNull LocalDateTime fecha,
        @NotNull int calificacion,
        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio,
        @NotBlank String mensaje,
        @NotBlank String respuesta
) {
}
