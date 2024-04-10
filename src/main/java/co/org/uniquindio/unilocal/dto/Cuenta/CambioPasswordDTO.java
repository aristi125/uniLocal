package co.org.uniquindio.unilocal.dto.Cuenta;

public record CambioPasswordDTO(
        String id,
        String passwordNueva,
        String email,
        String token
) {
}