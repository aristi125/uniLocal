package co.org.uniquindio.unilocal.dto.CuentaDTO;

public record CambioPasswordDTO(
        String id,
        String passwordNueva,
        String email,
        String token
) {
}