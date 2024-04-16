package co.org.uniquindio.unilocal.dto.comentario;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

//NOTA: Esta clase es para obtener el comentario con todos sus atributos
//es para hacer procesos
public record DetalleComentarioDTO(
        String codigoComentario,
        LocalDateTime fecha,
        int calificacion,
        String codigoCliente,
        String codigoNegocio,
        String mensaje,
        String respuesta
) {
}
