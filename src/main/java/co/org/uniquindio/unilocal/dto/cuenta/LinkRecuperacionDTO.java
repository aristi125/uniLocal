package co.org.uniquindio.unilocal.dto.cuenta;

import jakarta.validation.constraints.NotBlank;

public record LinkRecuperacionDTO(
        @NotBlank String idCuenta,
        @NotBlank String email
        ) {
}
