package co.org.uniquindio.unilocal.dto.comentario;

public record ComentarioDTO<T>(
    boolean error,
    T respuesta
) {
}
