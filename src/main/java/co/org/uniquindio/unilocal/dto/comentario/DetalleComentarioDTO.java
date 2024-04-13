package co.org.uniquindio.unilocal.dto.comentario;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

//NOTA: Esta clase es para obtener el comentario con todos sus atributos
//es para hacer procesos
public record DetalleComentarioDTO(
        LocalDateTime fecha,
        @NotBlank int calificacion,
        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio,
       @NotBlank String mensaje,
        String respuesta
) {
}
