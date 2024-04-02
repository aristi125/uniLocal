package co.org.uniquindio.unilocal.dto.CuentaDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SesionDTO(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) {
}
