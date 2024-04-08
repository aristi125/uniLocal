package co.org.uniquindio.unilocal.dto;

public record Comentario<T>(
    boolean error,
    T respuesta
) {
}
