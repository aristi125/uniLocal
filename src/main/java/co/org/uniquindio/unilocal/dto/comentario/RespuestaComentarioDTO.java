package co.org.uniquindio.unilocal.dto.comentario;


import jakarta.validation.constraints.NotBlank;

//esta clase es para dar respuesta a un comentario
public record RespuestaComentarioDTO (
        @NotBlank String codigoComentario,
        @NotBlank String codigoClienteReceptor,
        @NotBlank String codigoNegocio,
        @NotBlank String respuesta
) {
}