package co.org.uniquindio.unilocal.dto.comentario;


//esta clase es para dar respuesta a un comentario
public record RespuestaComentarioDTO (
        String codigoComentario,
        String codigoNegocio,
        String respuesta
) {
}