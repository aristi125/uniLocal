package co.org.uniquindio.unilocal.dto.CuentaDTO;

public record CambioPasswordDTO(
        String passwordNueva,
        String id,
        String token
) {
}