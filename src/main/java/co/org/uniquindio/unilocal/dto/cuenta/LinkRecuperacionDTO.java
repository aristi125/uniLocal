package co.org.uniquindio.unilocal.dto.cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LinkRecuperacionDTO(
        String idCuenta,
        @NotBlank @Email String email
        ) {
}
