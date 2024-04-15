package co.org.uniquindio.unilocal.dto;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
